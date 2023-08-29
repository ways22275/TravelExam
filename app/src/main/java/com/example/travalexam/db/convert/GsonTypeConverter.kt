package com.example.travalexam.db.convert

import androidx.room.TypeConverter
import com.example.travalexam.data.Image
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GsonTypeConverter {

  private val gson = Gson()

  @TypeConverter
  fun imagesToJson(list: List<Image>): String {
    val type = object : TypeToken<List<Image>>() {}.type
    return gson.toJson(list, type)
  }

  @TypeConverter
  fun jsonToImages(jsonStr: String): List<Image> {
    val type = object : TypeToken<List<Image>>() {}.type
    return gson.fromJson(jsonStr, type)
  }
}