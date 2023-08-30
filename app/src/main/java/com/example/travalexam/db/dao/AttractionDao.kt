package com.example.travalexam.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.travalexam.data.Attraction
import kotlinx.coroutines.flow.Flow

@Dao
interface AttractionDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAll(list: List<Attraction>)

  @Query("SELECT * FROM attraction ORDER BY seq ASC")
  fun getPagingSource(): PagingSource<Int, Attraction>

  @Query("SELECT * FROM attraction WHERE id = :id")
  fun getAttractionFlow(id: Int): Flow<Attraction?>

  @Query("DELETE FROM attraction")
  fun deleteAll()

}