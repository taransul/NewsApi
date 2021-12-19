package com.example.newsapi.domain.di

import com.example.newsapi.data.NewsInteractorImplRoom
import com.example.newsapi.domain.NewsInteractorRoom
import org.koin.dsl.module

val domainModule = module {
    single<NewsInteractorRoom> {
        NewsInteractorImplRoom(newsDao = get())
    }
}