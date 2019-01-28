package space.fanbox.android.fanbox.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import space.fanbox.android.fanbox.model.Letter

@Database(entities = [Letter::class], version = 1)
@TypeConverters(TagTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun letterDao(): LetterDao
}