package space.fanbox.android.fanbox.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import space.fanbox.android.fanbox.model.Letter

@Dao
interface LetterDao {

    @get:Query("SELECT * FROM letter")
    val all: List<Letter>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllLetters(vararg letters: Letter)
}