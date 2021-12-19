package com.example.newsapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapi.domain.NewsInteractor
import com.example.newsapi.domain.NewsInteractorRoom
import com.example.newsapi.network.dto.Article
import com.example.newsapi.network.dto.NewsResponse
import com.example.newsapi.presentation.recyclers.saved.News
import kotlinx.coroutines.launch

class NewsFragmentViewModel(
    private val interactor: NewsInteractor,
    private val newsInteractorRoom: NewsInteractorRoom
) : ViewModel() {

    private val _news = MutableLiveData<NewsResponse>()
    val news: LiveData<NewsResponse> get() = _news


    private val _newsSaved = MutableLiveData<List<News>>()
    val newsSaved: LiveData<List<News>> get() = _newsSaved

    init {
        loadNews()
    }

    private fun loadNews() {
        viewModelScope.launch {
            _news.value = interactor.getNews()
            _newsSaved.value = newsInteractorRoom.getNews()
        }
    }

    fun onNewsItemClicked(position: Int, activated: Boolean) {

        val item = _news.value?.articles?.get(position) ?: return

        val list = _news.value?.articles?.toMutableList() ?: return

        list[position] = item.copy(isChecked = !item.isChecked)

        _news.value = NewsResponse("ok", 0, list)

        if (!item.isChecked) {
            insertNews(list[position])
        } else if (item.isChecked) {
            deleteNews(list[position])
        }
    }

    private fun insertNews(news: Article) {
        viewModelScope.launch {
            newsInteractorRoom.insertNews(
                News(
                    news.author,
                    news.title,
                    news.description,
                    news.articleUrl,
                    news.previewUrl,
                    news.publishedAt
                )
            )
        }
    }

    private fun deleteNews(news: Article) {
        viewModelScope.launch {
            newsInteractorRoom.deleteNewsFun(
                News(
                    news.author,
                    news.title,
                    news.description,
                    news.articleUrl,
                    news.previewUrl,
                    news.publishedAt
                )
            )
        }
    }
}