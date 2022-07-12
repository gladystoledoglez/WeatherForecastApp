package com.example.weatherforecastapp.domain.models

import com.example.weatherforecastapp.data.entities.CityEntity
import com.example.weatherforecastapp.data.entities.ItemForecastEntity

data class ForecastModel(
    val list: List<ItemForecastEntity>? = null,
    val city: CityEntity? = null
)