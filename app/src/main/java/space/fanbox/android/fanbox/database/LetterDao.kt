package space.fanbox.android.fanbox.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Maybe
import space.fanbox.android.fanbox.model.Letter

@Dao
interface LetterDao {

    @get:Query("SELECT * FROM letter")
    val all: List<Letter>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllLetters(vararg letters: Letter)

    @Query("SELECT * FROM letter WHERE id = :id")
    fun loadLetterById(id: String) : Maybe<Letter>
}