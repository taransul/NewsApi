package com.example.newsapi.data

import com.example.newsapi.data.storage.NewsEntity
import com.example.newsapi.presentation.recyclers.saved.News

fun NewsEntity.toUser() =
    News(
        author = author,
        title = title,
        description = description,
        articleUrl = articleUrl,
        previewUrl = previewUrl,
        publishedAt = publishedAt,
    )


fun News.toUserEntity() =
    NewsEntity(
        author = author,
        title = title,
        description = description,
        articleUrl = articleUrl,
        previewUrl = previewUrl,
        publishedAt = publishedAt
    )