package com.ksnk.gif.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ksnk.gif.Images

class ImagesConverter {
    @TypeConverter
    fun fromImageList(value: List<Images>): String {
        val gson = Gson()
        val type = object : TypeToken<Images>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toImageList(value: String): Images {
        val gson = Gson()
        val type = object : TypeToken<Images>() {}.type
        return gson.fromJson(value, type)
    }
}