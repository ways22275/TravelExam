package com.example.travalexam.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.travalexam.data.RemoteKey

@Dao
interface RemoteKeyDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(key: RemoteKey)

  @Query("SELECT * FROM remoteKey")
  fun get(): RemoteKey?

  @Query("DELETE FROM remoteKey")
  fun deleteAll()
}