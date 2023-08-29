package com.example.travalexam.network

import com.example.travalexam.data.ApiResponse
import com.example.travalexam.data.Attraction
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface TravelService {

  @Headers("accept: application/json")
  @GET("{languageCode}/Attractions/All")
  suspend fun fetchAttractions(
    @Path("languageCode") languageCode: String,
    @Query("page") page: Int,
  ): ApiResponse<List<Attraction>>
}