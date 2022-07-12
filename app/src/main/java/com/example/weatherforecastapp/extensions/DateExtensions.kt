package com.example.weatherforecastapp.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.toDateFormatStr(): String? {
    val formatter = SimpleDateFormat(LONG_DATE, Locale.getDefault())
    try {
        formatter.format(this)

    } catch (e: Exception) {
        e.printStackTrace()
    }
    return formatter.format(this)
}

fun String.toDateFormat(): Date? {
    val formatter = SimpleDateFormat(SHORT_DATE, Locale.US)
    try {
        formatter.parse(this)

    } catch (e: Exception) {
        e.printStackTrace()
    }
    return formatter.parse(this)
}


const val SHORT_DATE = "yyyy-MM-dd hh:mm:ss"
const val LONG_DATE = "E, MMM dd yyyy HH:mm:ss"