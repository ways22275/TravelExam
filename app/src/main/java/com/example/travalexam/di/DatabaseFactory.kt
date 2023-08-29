package com.example.travalexam.di

import android.app.Application
import androidx.room.Room
import com.example.travalexam.db.RoomDatabase

fun provideDatabase(application: Application): RoomDatabase {
  return Room
    .databaseBuilder(application, RoomDatabase::class.java, "RoomDatabase")
    .fallbackToDestructiveMigration()
    .build()
}