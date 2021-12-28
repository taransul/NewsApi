package com.example.newsapi.data

import com.example.newsapi.data.storage.NewsEntity
import com.example.newsapi.network.dto.Article
import com.example.newsapi.presentation.recyclers.News

private const val PREVIEW_URL_DEFAULT =
    "https://city53.ru/files/3c5/3c50714cfedd60e10cc9a2055c022d09.jpg"
private const val ARTICLE_URL_DEFAULT =
    "https://yt3.ggpht.com/a/AATXAJwdADKSD8gpKW5D8fotNVkqJVLKfa-fuGoAuA=s900-c-k-c0xffffffff-no-rj-mo"
private const val AUTHOR_DEFAULT = "автор не известен"
private const val TITLE_DEFAULT = "нет заголовка"
private const val DESCRIPTION_DEFAULT = "нет описания"
private const val PUBLISHED_AT_DEFAULT = "дата не известна"


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
        author = author ?: AUTHOR_DEFAULT,
        title = title ?: TITLE_DEFAULT,
        description = description ?: DESCRIPTION_DEFAULT,
        articleUrl = articleUrl ?: ARTICLE_URL_DEFAULT,
        previewUrl = previewUrl ?: PREVIEW_URL_DEFAULT,
        publishedAt = publishedAt ?: PUBLISHED_AT_DEFAULT,
        isChecked = false
    )