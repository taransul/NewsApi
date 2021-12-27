package com.example.newsapi.data.di

import androidx.room.Room
import com.example.newsapi.data.storage.AppDatabase
import org.koin.dsl.module

val dataModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "news3"
        ).build()
    }

    single {
        get<AppDatabase>().getUserDao()
    }
}