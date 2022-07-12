package com.example.weatherforecastapp.domain.models

import com.example.weatherforecastapp.data.entities.StatisticsEntity
import com.example.weatherforecastapp.data.entities.WeatherEntity
import com.example.weatherforecastapp.data.entities.WindEntity

data class ItemForecastModel(
    val statistics: StatisticsEntity? = null,
    val weather: List<WeatherEntity>? = null,
    val wind: WindEntity? = null,
    val dtTxt: String? = null
) {
}