package com.example.newsapi.presentation.recyclers

interface OnNewsClickListener {
    fun onIconClickListener(position: Int)

    fun onItemClickListener(news: News)
}