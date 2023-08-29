package com.example.travalexam.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "attraction")
@Parcelize
data class Attraction(
  @PrimaryKey(autoGenerate = true)
  val seq: Int,

  val id: Int,

  val name: String,

  val introduction: String,

  val address: String,

  @SerializedName("official_site")
  val officialSite: String,

  @SerializedName("modified")
  val modifiedTime: String,

  val images: List<Image>
) : Parcelable
