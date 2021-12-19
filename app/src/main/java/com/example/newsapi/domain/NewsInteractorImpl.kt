package com.example.newsapi.domain

import com.example.newsapi.network.NewsApi
import com.example.newsapi.network.dto.NewsResponse

class NewsInteractorImpl(private val newsApi: NewsApi) : NewsInteractor {

    override suspend fun getNews(): NewsResponse {
        return newsApi.getEverything(
            query = "technology",
            fromDate = null,
            toDate = null,
            language = "ru",
            sortBy = "publishedAt"
        )
    }
}