package com.example.newsapi.presentation.recyclers

data class News(
    val author: String,
    val title: String,
    val description: String,
    val articleUrl: String,
    val previewUrl: String,
    val publishedAt: String,
    val isChecked: Boolean
)