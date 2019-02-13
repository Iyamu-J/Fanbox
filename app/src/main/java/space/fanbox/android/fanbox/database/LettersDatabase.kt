package space.fanbox.android.fanbox.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import space.fanbox.android.fanbox.model.Letter

@Database(entities = [Letter::class], version = 1, exportSchema = false)
@TypeConverters(TagTypeConverters::class)
abstract class LettersDatabase : RoomDatabase() {

    abstract fun letterDao(): LetterDao

    companion object {

        @Volatile private var INSTANCE: LettersDatabase? = null

        fun getInstance(context: Context): LettersDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context): LettersDatabase =
                Room.databaseBuilder(
                    context.applicationContext,
                    LettersDatabase::class.java,
                    "letters.db"
                )
                    .build()
    }
}