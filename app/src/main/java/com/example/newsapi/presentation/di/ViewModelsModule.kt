package com.example.newsapi.presentation.di

import com.example.newsapi.domain.NewsInteractor
import com.example.newsapi.domain.NewsInteractorImpl
import com.example.newsapi.network.NewsApi
import com.example.newsapi.network.RetrofitClient
import com.example.newsapi.presentation.NewsFragmentViewModel
import org.koin.android.viewmodel.dsl.viewModel

import org.koin.dsl.module

val mainModule = module {
    viewModel {
        NewsFragmentViewModel(
            interactor = get(),
            newsInteractorRoom = get()
        )
    }

    single<NewsInteractor> {
        NewsInteractorImpl(
            newsApi = get()
        )
    }

    single<NewsApi> {
        RetrofitClient.getNewsApi()
    }
}