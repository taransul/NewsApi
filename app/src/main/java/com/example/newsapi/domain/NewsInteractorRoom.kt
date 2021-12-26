package com.example.newsapi.domain

import com.example.newsapi.presentation.recyclers.News

interface NewsInteractorRoom {
    suspend fun getSavedNews(): List<News>

    suspend fun insertSavedNews(vararg arrayOfNews: News)

    suspend fun deleteOneSavedNews(newsTitle: String?)
}