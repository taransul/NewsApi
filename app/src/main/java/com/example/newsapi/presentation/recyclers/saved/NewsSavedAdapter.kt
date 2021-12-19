package com.example.newsapi.presentation.recyclers.saved

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapi.presentation.recyclers.news.OnNewsClickListener

class NewsSavedAdapter(private val itemClickListener: OnNewsClickListener) :
    RecyclerView.Adapter<NewsSavedViewHolder>() {
    private var items: List<News> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsSavedViewHolder =
        NewsSavedViewHolder.fromParent2(parent, itemClickListener)

    override fun onBindViewHolder(holderSaved: NewsSavedViewHolder, position: Int) {
        holderSaved.bindView2(items[position])
    }

    override fun getItemCount() = items.size

    fun submitList2(data: List<News>) {
        items = data
        notifyDataSetChanged()
    }
}