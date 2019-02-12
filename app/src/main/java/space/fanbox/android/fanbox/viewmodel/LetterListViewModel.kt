package space.fanbox.android.fanbox.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import space.fanbox.android.fanbox.R
import space.fanbox.android.fanbox.database.LetterDao
import space.fanbox.android.fanbox.di.BaseViewModel
import space.fanbox.android.fanbox.model.Letter
import space.fanbox.android.fanbox.rest.WebService
import space.fanbox.android.fanbox.ui.LetterListAdapter
import space.fanbox.android.fanbox.utils.RefreshInterval
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LetterListViewModel(private val letterDao: LetterDao) : BaseViewModel() {

    @Inject
    lateinit var webService: WebService

    private lateinit var subscription: Disposable
    private var compositeDisposable: CompositeDisposable

    private val refreshInterval: RefreshInterval<String>

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorOnClickListener = View.OnClickListener {
        loadLetters()
    }

    val letterListAdapter: LetterListAdapter = LetterListAdapter()

    init {
        loadLetters()
        compositeDisposable = CompositeDisposable()
        refreshInterval = RefreshInterval(30, TimeUnit.MINUTES)
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
        compositeDisposable.clear()
    }

    private fun loadLetters() {
        subscription = Observable.fromCallable { letterDao.all }
            .concatMap { dbLetterList ->
                if (dbLetterList.isEmpty()) {
                    webService.getLetters().concatMap { apiLetterList ->
                        letterDao.insertAllLetters(*apiLetterList.toTypedArray())
                        Observable.just(apiLetterList)
                    }
                } else {
                    if (refreshInterval.shouldFetch("letters")) {
                        refreshLetters()
                    }
                    Observable.just(dbLetterList)
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveLetterListStart() }
            .doOnTerminate { onRetrieveLetterListFinish() }
            .doAfterNext {

            }
            .subscribe(
                { result -> onRetrieveLetterListSuccess(result) },
                { error ->
                    onRetrieveLetterListError(error)
                    refreshInterval.reset("letters")
                }
            )
    }

    private fun refreshLetters() {

        compositeDisposable.add(
            webService.getLetters()
                .subscribeOn(Schedulers.io())
                .subscribe { result -> updateLocalSource(result) }
        )
        Log.i(LetterListViewModel::class.java.simpleName, "New Updates")
    }

    private fun onRetrieveLetterListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveLetterListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveLetterListSuccess(letterList: List<Letter>) {
        letterListAdapter.setLetterList(letterList)
        errorMessage.value = null
    }

    private fun onRetrieveLetterListError(error: Throwable) {
        errorMessage.value = R.string.error_message
        Log.i(LetterListViewModel::class.java.simpleName, error.message)
    }

    private fun updateLocalSource(letterList: List<Letter>) {
        Completable
            .fromAction {
                letterDao.insertAllLetters(*letterList.toTypedArray())
            }
            .subscribeOn(Schedulers.io())
            .subscribe()

        Log.i(LetterListViewModel::class.java.simpleName, "Updates Added to DB")
    }
}