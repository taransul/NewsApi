package com.example.newsapi.domain

import com.example.newsapi.presentation.recyclers.saved.News

interface NewsInteractorRoom {
    suspend fun getNews(): List<News>

    suspend fun insertNews(vararg arrayOfNews: News)

    suspend fun deleteNewsFun (vararg arrayOfNews: News)
}