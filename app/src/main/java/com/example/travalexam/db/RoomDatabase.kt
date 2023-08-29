package com.example.travalexam.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.travalexam.data.Attraction
import com.example.travalexam.data.RemoteKey
import com.example.travalexam.db.convert.GsonTypeConverter
import com.example.travalexam.db.dao.AttractionDao
import com.example.travalexam.db.dao.RemoteKeyDao

@Database(
  entities = [Attraction::class, RemoteKey::class],
  version = 1,
  exportSchema = false
)
@TypeConverters(GsonTypeConverter::class)
abstract class RoomDatabase : RoomDatabase() {
  abstract fun getAttractionDao(): AttractionDao
  abstract fun getRemoteKeyDao(): RemoteKeyDao
}