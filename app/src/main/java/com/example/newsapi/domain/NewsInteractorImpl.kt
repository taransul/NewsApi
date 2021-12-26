package com.example.newsapi.domain

import com.example.newsapi.data.toNews
import com.example.newsapi.presentation.recyclers.News

class NewsInteractorImpl(
    private val interactorRoom: NewsInteractorRoom,
    private val interactor: NetworkInteractor,
) : NewsInteractor {
    override suspend fun getNews(): List<News> {
        val savedNews = interactorRoom.getSavedNews()
        val articles = interactor.getNewsInteractor()

        val list1: List<News> = savedNews.map { dataBaseNews ->
            dataBaseNews
        }

        val list2: List<News> = articles.map { networkNews ->
            networkNews.toNews()
        }

        val list3: MutableList<News> = mutableListOf()

        list2.forEach { item ->
            val newItem = list1.find { news ->
                item.title == news.title
            }
            list3.add(
                newItem ?: item
            )

        }
        return list3
    }
}