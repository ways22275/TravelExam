package com.example.travalexam.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.travalexam.data.Attraction
import com.example.travalexam.data.Language
import com.example.travalexam.data.RemoteKey
import com.example.travalexam.data.getLangCode
import com.example.travalexam.db.RoomDatabase
import com.example.travalexam.network.TravelService
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException

const val DEFAULT_PAGE = 1

@OptIn(ExperimentalPagingApi::class)
class AttractionRemoteMediator(
  private val service: TravelService,
  private val db: RoomDatabase,
  private val lang: Language?
) : RemoteMediator<Int, Attraction>() {

  override suspend fun initialize(): InitializeAction {
    return InitializeAction.LAUNCH_INITIAL_REFRESH
  }

  override suspend fun load(loadType: LoadType, state: PagingState<Int, Attraction>): MediatorResult {
    println("AttractionRemoteMediator loadType : $loadType")

    val page = when (loadType) {
      LoadType.REFRESH -> DEFAULT_PAGE
      LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
      LoadType.APPEND -> {
        // Query DB for SubredditRemoteKey for the subreddit.
        // SubredditRemoteKey is a wrapper object we use to keep track of page keys we
        // receive from the Reddit API to fetch the next or previous page.
        val remoteKey = db.withTransaction {
          db.getRemoteKeyDao().get()
        }

        // We must explicitly check if the page key is null when appending, since the
        // Reddit API informs the end of the list by returning null for page key, but
        // passing a null key to Reddit API will fetch the initial page.
        if (remoteKey?.nextKey == null) {
          return MediatorResult.Success(endOfPaginationReached = true)
        }
        remoteKey.nextKey
      }
    }

    try {
      val apiResponse = service.fetchAttractions(page = page, languageCode = lang.getLangCode())
      val data = apiResponse.data
      val endOfPaginationReached = data?.isEmpty() == true

      db.withTransaction {
        if (loadType == LoadType.REFRESH) {
          db.getRemoteKeyDao().deleteAll()
          db.getAttractionDao().deleteAll()
        }

        data?.let {
          db.getRemoteKeyDao().insert(RemoteKey(0, page + 1))
          db.getAttractionDao().insertAll(it)
        }
      }
      return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
    } catch (error: IOException) {
      return MediatorResult.Error(error)
    } catch (error: HttpException) {
      return MediatorResult.Error(error)
    }
  }
}