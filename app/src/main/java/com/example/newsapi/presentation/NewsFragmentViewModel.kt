package com.example.newsapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapi.domain.NewsInteractor
import com.example.newsapi.domain.NewsInteractorRoom
import com.example.newsapi.presentation.recyclers.News
import kotlinx.coroutines.launch

class NewsFragmentViewModel(
    private val newsInteractorRoom: NewsInteractorRoom,
    private val article: NewsInteractor,
) : ViewModel() {

    private val _newsNetwork = MutableLiveData<List<News>>()
    val newsNetwork: LiveData<List<News>> get() = _newsNetwork

    private val _newsSaved = MutableLiveData<List<News>>()
    val newsSaved: LiveData<List<News>> get() = _newsSaved

    init {
        loadNews()
    }

    private fun loadNews() {
        viewModelScope.launch {
            _newsSaved.value = newsInteractorRoom.getSavedNews()
            _newsNetwork.value = article.getNews()
        }
    }

    fun onNewsItemClicked(position: Int) {
        val item = _newsNetwork.value?.get(position) ?: return
        val list = _newsNetwork.value?.toMutableList() ?: return

        list[position] = item.copy(isChecked = !item.isChecked)
        _newsNetwork.value = list

        if (item.isChecked) {
            deleteNews(list[position].title)
        } else {
            insertNews(list[position])
        }
    }

    private fun insertNews(news: News) {
        viewModelScope.launch {
            newsInteractorRoom.insertSavedNews(
                News(
                    news.author,
                    news.title,
                    news.description,
                    news.articleUrl,
                    news.previewUrl,
                    news.publishedAt,
                    news.isChecked
                )
            )
        }
    }

    private fun deleteNews(news: String?) {
        viewModelScope.launch {
            newsInteractorRoom.deleteOneSavedNews(news)
        }
    }
}