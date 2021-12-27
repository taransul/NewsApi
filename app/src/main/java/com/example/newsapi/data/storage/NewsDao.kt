package com.example.newsapi.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NewsDao {

    @Query("SELECT * FROM news3")
    fun getAll(): List<NewsEntity>

    @Insert
    fun insertUser(news: NewsEntity)

    @Query("DELETE FROM news3 WHERE title = :title")
    fun deleteUser(title: String?)
}