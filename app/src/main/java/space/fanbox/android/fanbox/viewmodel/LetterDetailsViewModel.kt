package space.fanbox.android.fanbox.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import space.fanbox.android.fanbox.database.LettersDatabase
import space.fanbox.android.fanbox.model.Letter

class LetterDetailsViewModel(private val lettersDatabase: LettersDatabase, private val letterId: String) : ViewModel() {

    private var mLetter = MutableLiveData<Letter>()
    private lateinit var disposable: Disposable

    init {
        loadLetterById()
    }

    private fun loadLetterById() {
        disposable = lettersDatabase.letterDao()
            .loadLetterById(letterId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { letter -> mLetter.value = letter }
    }

    fun getLetter(): MutableLiveData<Letter> {
        return mLetter
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}