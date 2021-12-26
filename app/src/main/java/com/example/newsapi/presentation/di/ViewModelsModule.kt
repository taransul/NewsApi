package com.example.newsapi.presentation.di

import com.example.newsapi.domain.NetworkInteractor
import com.example.newsapi.domain.NetworkInteractorImpl
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
            newsInteractorRoom = get(),
            article = get()
        )
    }

    single<NewsInteractor> {
        NewsInteractorImpl(
            interactorRoom = get(),
            interactor = get()
        )
    }

    single<NetworkInteractor> {
        NetworkInteractorImpl(
            newsApi = get(),
        )
    }

    single<NewsApi> {
        RetrofitClient.getNewsApi()
    }
}