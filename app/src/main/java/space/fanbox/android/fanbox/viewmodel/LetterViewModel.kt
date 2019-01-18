package space.fanbox.android.fanbox.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import space.fanbox.android.fanbox.di.BaseViewModel
import space.fanbox.android.fanbox.rest.WebService
import javax.inject.Inject

class LetterViewModel: BaseViewModel() {

    @Inject
    lateinit var webService: WebService

    private lateinit var subscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

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
                { onRetrieveLetterListSuccess() },
                { onRetrieveLetterListError() }
            )
    }

    private fun onRetrieveLetterListStart() {
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrieveLetterListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveLetterListSuccess() {}

    private fun onRetrieveLetterListError() {}
}