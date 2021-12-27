package com.example.newsapi.presentation.recyclers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.newsapi.R
import com.example.newsapi.databinding.ItemLayoutBinding
import com.example.newsapi.utils.ParseDate

class NewsViewHolder(
    itemView: View,
    private val itemClickListener: OnNewsClickListener,
) : RecyclerView.ViewHolder(itemView) {
    private val binding: ItemLayoutBinding by viewBinding()

    companion object {
        fun fromParent(parent: ViewGroup, itemClickListener: OnNewsClickListener) =
            NewsViewHolder(
                LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_layout, parent, false),
                itemClickListener
            )
    }

    private val authorTextView: TextView by lazy { binding.authorTextView }
    private val titleTextView: TextView by lazy { binding.titleTextView }
    private val descriptionTextView: TextView by lazy { binding.descriptionTextView }
    private val publishedAtTextView: TextView by lazy { binding.publishedAtTextView }

    private val iconCheckBox: CheckBox by lazy { binding.checkBox }
    private val itemContainer by lazy { binding.itemContainer }
    private val headImage by lazy { binding.headImage }

    fun bindView(item: News) {
        authorTextView.text = item.author
        titleTextView.text = item.title
        descriptionTextView.text = item.description
        publishedAtTextView.text = ParseDate.parseDate(item.publishedAt)
        loadImageByUrl(item.previewUrl)

        iconCheckBox.isChecked = item.isChecked

        itemContainer.setOnClickListener {
            itemClickListener.onItemClickListener(item)
        }

        iconCheckBox.setOnClickListener {
            itemClickListener.onIconClickListener(absoluteAdapterPosition)
        }
    }

    private fun loadImageByUrl(url: String?) {
        Glide.with(headImage.context)
            .load(url)
            .into(headImage)
    }
}