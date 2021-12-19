package com.example.newsapi.data

import com.example.newsapi.data.storage.NewsDao
import com.example.newsapi.domain.NewsInteractorRoom
import com.example.newsapi.presentation.recyclers.saved.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsInteractorImplRoom(private val newsDao: NewsDao) : NewsInteractorRoom {
    override suspend fun getNews(): List<News> {
        return withContext(Dispatchers.IO) {
            newsDao.getAll().map { newsEntity -> newsEntity.toUser() }
        }
    }

    override suspend fun insertNews(vararg arrayOfNews: News) {
        withContext(Dispatchers.IO) {
            arrayOfNews
                .map { domainNews -> domainNews.toUserEntity() }
                .forEach { newsEntity -> newsDao.insertUser(newsEntity) }
        }
    }

    override suspend fun deleteNewsFun(vararg arrayOfNews: News) {
        withContext(Dispatchers.IO) {
            arrayOfNews
                .map { domainNews -> domainNews.toUserEntity() }
                .forEach { newsEntity -> newsDao.deleteUser() }
        }
    }
}