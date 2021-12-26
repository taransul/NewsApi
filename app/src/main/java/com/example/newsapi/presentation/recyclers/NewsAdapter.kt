package com.example.newsapi.presentation.recyclers

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class NewsAdapter(
    private val itemClickListener: OnNewsClickListener,
) : RecyclerView.Adapter<NewsViewHolder>() {

    private var items: List<News> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder.fromParent(parent, itemClickListener)

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount() = items.size

    fun submitList(data: List<News>) {
        items = data
        notifyDataSetChanged()
    }
}