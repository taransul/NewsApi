package com.example.newsapi.data

import com.example.newsapi.data.storage.NewsEntity
import com.example.newsapi.network.dto.Article
import com.example.newsapi.presentation.recyclers.News

fun NewsEntity.toUser() =
    News(
        author = author,
        title = title,
        description = description,
        articleUrl = articleUrl,
        previewUrl = previewUrl,
        publishedAt = publishedAt,
        isChecked = true
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

fun Article.toNews() =
    News(
        author = author ?: "",
        title = title ?: "",
        description = description ?: "",
        articleUrl = articleUrl ?: "",
        previewUrl = previewUrl ?: "",
        publishedAt = publishedAt ?: "",
        isChecked = false
    )