package com.example.newsapi.data.storage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news2")
data class NewsEntity(
    @ColumnInfo(name = "author")
    val author: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "articleUrl")
    val articleUrl: String,
    @ColumnInfo(name = "previewUrl")
    val previewUrl: String,
    @ColumnInfo(name = "publishedAt")
    val publishedAt: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
