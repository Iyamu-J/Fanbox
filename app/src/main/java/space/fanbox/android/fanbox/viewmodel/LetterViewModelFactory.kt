package space.fanbox.android.fanbox.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import space.fanbox.android.fanbox.database.LettersDatabase

class LetterViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LetterListViewModel::class.java)) {
            val db = LettersDatabase.getInstance(context)

            return LetterListViewModel(db.letterDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}