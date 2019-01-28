package space.fanbox.android.fanbox.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import space.fanbox.android.fanbox.database.AppDatabase

class LetterViewModelFactory(private val activity: AppCompatActivity) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LetterListViewModel::class.java)) {
            val db = Room.databaseBuilder(
                activity.applicationContext,
                AppDatabase::class.java,
                "letters"
            )
                .build()

            return LetterListViewModel(db.letterDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}