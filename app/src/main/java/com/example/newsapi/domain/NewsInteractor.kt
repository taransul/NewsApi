package com.example.newsapi.domain

import com.example.newsapi.network.dto.NewsResponse

interface NewsInteractor {
    suspend fun getNews(): NewsResponse
}