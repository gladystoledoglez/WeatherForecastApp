package com.example.weatherforecastapp.data.remote

import kotlinx.coroutines.flow.flow

class WeatherRemoteDataSource(private val service: ForecastService) {

    fun getWeatherForecast(city: String?) = flow { emit(service.getWeatherForecast(city)) }
}