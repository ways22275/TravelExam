package com.example.travalexam.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.travalexam.data.Language
import com.example.travalexam.db.RoomDatabase
import com.example.travalexam.network.TravelService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalPagingApi::class)
class TravelRepository(service: TravelService, private val db: RoomDatabase) {

  val currentLanguage = MutableLiveData(Language.ZH_TW)

  val attractionsFlow = Pager(
    config = PagingConfig(pageSize = 30),
    pagingSourceFactory = { db.getAttractionDao().getPagingSource() },
    remoteMediator = AttractionRemoteMediator(service, db, currentLanguage)
  ).flow

  suspend fun getAttractionFlow(id: Int) = withContext(Dispatchers.IO) {
    db.getAttractionDao().getAttractionFlow(id)
  }
}