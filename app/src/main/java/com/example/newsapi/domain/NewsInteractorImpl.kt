package com.example.newsapi.domain

import com.example.newsapi.data.toNews
import com.example.newsapi.presentation.recyclers.News

class NewsInteractorImpl(
    private val interactorRoom: NewsInteractorRoom,
    private val interactor: NetworkInteractor,
) : NewsInteractor {
    override suspend fun getNews(): List<News> {
        val savedNews = interactorRoom.getSavedNews()
        val articles = interactor.getNewsNetwork()

        val savedNewsList: List<News> = savedNews.map { dataBaseNews ->
            dataBaseNews
        }

        val articlesList: List<News> = articles.map { networkNews ->
            networkNews.toNews()
        }

        val comparisonResultList: MutableList<News> = mutableListOf()

        articlesList.forEach { item ->
            val newItem = savedNewsList.find { news ->
                item.title == news.title
            }
            comparisonResultList.add(
                newItem ?: item
            )
        }
        return comparisonResultList
    }
}