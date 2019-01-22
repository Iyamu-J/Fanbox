package space.fanbox.android.fanbox.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import space.fanbox.android.fanbox.R
import space.fanbox.android.fanbox.di.BaseViewModel
import space.fanbox.android.fanbox.model.Letter
import space.fanbox.android.fanbox.rest.WebService
import space.fanbox.android.fanbox.ui.LetterListAdapter
import javax.inject.Inject

class LetterListViewModel: BaseViewModel() {

    @Inject
    lateinit var webService: WebService

    private lateinit var subscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorOnClickListener = View.OnClickListener {
        loadLetters()
    }

    val letterListAdapter: LetterListAdapter = LetterListAdapter()

    init {
        loadLetters()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadLetters() {
        subscription = webService.getLetters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveLetterListStart() }
            .doOnTerminate { onRetrieveLetterListFinish() }
            .subscribe(
                { result -> onRetrieveLetterListSuccess(result) },
                { error -> onRetrieveLetterListError(error) }
            )
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
        Log.i(LetterListViewModel::class.java.simpleName, letterList.toString())
    }

    private fun onRetrieveLetterListError(error: Throwable) {
        errorMessage.value = R.string.error_message
        Log.i(LetterListViewModel::class.java.simpleName, error.message)
    }
}