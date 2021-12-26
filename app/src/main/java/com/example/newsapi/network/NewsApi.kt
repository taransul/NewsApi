package com.example.newsapi.network

import com.example.newsapi.network.dto.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("everything")
    suspend fun getEverything(
        @Query("q") query: String?,
        @Query("from") fromDate: String?,
        @Query("to") toDate: String?,
        @Query("language") language: String?,
        @Query("sortBy") sortBy: String,
        @Query("page") pageNumber: Int = 1,
        @Query("sources") sources: String = "",
        @Query("pageSize") pageSize: Int = 100,
        @Query("apiKey") apiKey: String = RetrofitClient.BASE_API_KEY
    ): NewsResponse
}