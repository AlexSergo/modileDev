package model

import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class ListConverter {
    @TypeConverter
    fun fromString(value: String): List<Node> {
        val listType = object : TypeToken<List<Node>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Node>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}
