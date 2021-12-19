package com.example.newsapi.presentation

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object ParseDate {
    fun parseDate(item: String?): String {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
        val date: Date = dateFormat.parse(item)
        val formatter: DateFormat = SimpleDateFormat("HH:mm   dd.MM.yyyy")
        val dateStr: String = formatter.format(date)
        return dateStr
    }
}