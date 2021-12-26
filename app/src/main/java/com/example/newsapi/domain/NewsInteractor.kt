package com.example.newsapi.domain

import com.example.newsapi.presentation.recyclers.News

interface NewsInteractor {
    suspend fun getNews(): List<News>
}