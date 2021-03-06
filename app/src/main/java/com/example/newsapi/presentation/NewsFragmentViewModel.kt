package com.example.newsapi.presentation

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsapi.domain.NewsInteractor
import com.example.newsapi.domain.NewsInteractorRoom
import com.example.newsapi.presentation.recyclers.News
import kotlinx.coroutines.launch

const val TEXT_WHEN_DELETED_FROM_SAVED_FRAGMENT = "Новость удалена"
const val TEXT_WHEN_INSERTED_INTO_SAVED_FRAGMENT = "Новость сохранена"

class NewsFragmentViewModel(
    private val newsInteractorRoom: NewsInteractorRoom,
    private val article: NewsInteractor,
    application: Application,
) : AndroidViewModel(application) {

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

    fun onNewsItemClicked(position: Int, isFragment: Boolean) {

        val listSaved = _newsSaved.value?.toMutableList() ?: return
        val item = _newsNetwork.value?.get(position) ?: return
        val list = _newsNetwork.value?.toMutableList() ?: return

        if (!isFragment) {
            list[position] = item.copy(isChecked = !item.isChecked)
            _newsNetwork.value = list

            if (item.isChecked) {
                deleteNews(list[position].title)

                Toast.makeText(getApplication(),
                    TEXT_WHEN_DELETED_FROM_SAVED_FRAGMENT,
                    Toast.LENGTH_SHORT).show()
            } else {
                insertNews(list[position])

                Toast.makeText(getApplication(),
                    TEXT_WHEN_INSERTED_INTO_SAVED_FRAGMENT,
                    Toast.LENGTH_SHORT).show()
            }
        } else if (isFragment) {
            deleteNews(listSaved[position].title)

            Toast.makeText(getApplication(),
                TEXT_WHEN_DELETED_FROM_SAVED_FRAGMENT,
                Toast.LENGTH_SHORT).show()

            loadNews()
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