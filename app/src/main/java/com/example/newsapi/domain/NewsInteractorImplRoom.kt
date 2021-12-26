package com.example.newsapi.domain

import com.example.newsapi.data.storage.NewsDao
import com.example.newsapi.data.toUser
import com.example.newsapi.data.toUserEntity
import com.example.newsapi.presentation.recyclers.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsInteractorImplRoom(private val newsDao: NewsDao) : NewsInteractorRoom {
    override suspend fun getSavedNews(): List<News> {
        return withContext(Dispatchers.IO) {
            newsDao.getAll().map { newsEntity -> newsEntity.toUser() }
        }
    }

    override suspend fun insertSavedNews(vararg arrayOfNews: News) {
        withContext(Dispatchers.IO) {
            arrayOfNews
                .map { domainNews -> domainNews.toUserEntity() }
                .forEach { newsEntity -> newsDao.insertUser(newsEntity) }
        }
    }

    override suspend fun deleteOneSavedNews(newsTitle: String?) {
        withContext(Dispatchers.IO) {
            newsDao.deleteUser(newsTitle)
        }
    }
}