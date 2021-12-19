package com.example.newsapi.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    const val BASE_API_KEY = "25dd0a07ee324735909247b0369af3b9"
    private const val BASE_URL = "https://newsapi.org/v2/"
    private const val CONNECT_TIMEOUT: Long = 60 // 60 seconds
    private const val READ_WRITE_TIMEOUT: Long = 120 // 120 seconds

    private var gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    private fun getClient(url: String = BASE_URL) = Retrofit.Builder()
        .baseUrl(url)
        .client(
            OkHttpClient().newBuilder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(READ_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun getNewsApi(): NewsApi = getClient().create(NewsApi::class.java)
}