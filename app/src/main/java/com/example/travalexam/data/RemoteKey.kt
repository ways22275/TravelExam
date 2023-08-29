package com.example.travalexam.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remoteKey")
data class RemoteKey(
  @PrimaryKey(autoGenerate = false)
  val id: Int,
  val nextKey: Int?,
)
