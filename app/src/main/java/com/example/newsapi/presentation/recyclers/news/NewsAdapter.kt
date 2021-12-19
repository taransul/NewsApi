package com.example.newsapi.presentation.recyclers.news

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapi.network.dto.Article
import com.example.newsapi.network.dto.NewsResponse

class NewsAdapter(
    private val itemClickListener: OnNewsClickListener
) : RecyclerView.Adapter<NewsViewHolder>() {

    private var items: List<Article> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder.fromParent(parent, itemClickListener)

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount() = items.size

    fun submitList(data: NewsResponse) {
        items = data.articles
        notifyDataSetChanged()
    }
}