package com.example.newsapi.domain

import com.example.newsapi.network.dto.Article
import com.example.newsapi.network.dto.NewsResponse

interface NetworkInteractor {
    suspend fun getNewsDoNotChange(): NewsResponse

    suspend fun getNewsInteractor(): List<Article>
}