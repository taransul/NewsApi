package com.example.newsapi.presentation.recyclers.news

interface OnNewsClickListener {
    fun onIconClickListener(position: Int, activated: Boolean)
    fun <T> onItemClickListener(news: T)
}