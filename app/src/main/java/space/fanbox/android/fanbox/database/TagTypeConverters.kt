package space.fanbox.android.fanbox.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import space.fanbox.android.fanbox.model.Tag
import java.util.*

class TagTypeConverters {

    @TypeConverter
    fun stringToTagList(data: String): List<Tag> {
        val type = object : TypeToken<List<Tag>>() {}.type
        return Gson().fromJson(data, type) ?: Collections.emptyList()
    }

    @TypeConverter
    fun tagListToString(tagList: List<Tag>): String {
        return Gson().toJson(tagList)
    }
}