package com.example.weatherforecastapp.domain.repository

import com.example.weatherforecastapp.domain.models.WeatherModel
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeatherForecast(city: String?): Flow<List<WeatherModel>?>
}