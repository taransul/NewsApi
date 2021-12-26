package com.example.newsapi.domain

import com.example.newsapi.network.NewsApi
import com.example.newsapi.network.dto.Article
import com.example.newsapi.network.dto.NewsResponse

class NetworkInteractorImpl(
    private val newsApi: NewsApi,
) : NetworkInteractor {

    override suspend fun getNewsDoNotChange(): NewsResponse {
        return newsApi.getEverything(
            query = "technology",
            fromDate = null,
            toDate = null,
            language = "ru",
            sortBy = "publishedAt"
        )
    }

    override suspend fun getNewsInteractor(): List<Article> {
        return getNewsDoNotChange().articles
    }
}
