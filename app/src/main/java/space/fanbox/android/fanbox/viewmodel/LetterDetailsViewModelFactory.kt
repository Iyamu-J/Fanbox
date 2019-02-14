package space.fanbox.android.fanbox.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import space.fanbox.android.fanbox.database.LettersDatabase

class LetterDetailsViewModelFactory(
    private val lettersDatabase: LettersDatabase,
    private val letterId: String
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LetterDetailsViewModel::class.java)) {
            return LetterDetailsViewModel(lettersDatabase, letterId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}