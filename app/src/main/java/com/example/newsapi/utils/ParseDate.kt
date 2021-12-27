package com.example.newsapi.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object ParseDate {
    private const val INCOMING_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX"
    private const val OUTGOING_DATE_FORMAT = "HH:mm   dd.MM.yyyy"

    fun parseDate(item: String?): String? {
        return try {
            val dateFormat: DateFormat = SimpleDateFormat(INCOMING_DATE_FORMAT)
            val date: Date = dateFormat.parse(item)
            val formatter: DateFormat = SimpleDateFormat(OUTGOING_DATE_FORMAT)
            formatter.format(date)
        } catch (e: Exception) {
            "$item (ошибка парсинга)"
        }
    }
}