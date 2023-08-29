package com.example.travalexam.di

import com.example.travalexam.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory

inline fun <reified T> provideService(retrofit: Retrofit): T = retrofit.create(T::class.java)

fun provideRetrofit(): Retrofit {
  return Builder().apply {
    baseUrl(BuildConfig.BASE_API_URL)
    client(
      OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
          level = HttpLoggingInterceptor.Level.BODY
        }).build()
    )
    addConverterFactory(
      GsonConverterFactory.create(
        GsonBuilder()
          .setLenient()
          .create()
      )
    )
  }.build()
}




