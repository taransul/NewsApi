package com.example.newsapi

import android.app.Application
import com.example.newsapi.data.di.dataModule
import com.example.newsapi.domain.di.domainModule
import com.example.newsapi.presentation.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(
                mainModule,
                dataModule,
                domainModule
            )
        }
    }
}