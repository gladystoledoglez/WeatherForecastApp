package com.example.weatherforecastapp.data.repository

import com.example.weatherforecastapp.data.mappers.toWeatherModelList
import com.example.weatherforecastapp.data.remote.WeatherRemoteDataSource
import com.example.weatherforecastapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.map

class WeatherDataRepository(
    private val remoteDataSource: WeatherRemoteDataSource
) : WeatherRepository {

    override fun getWeatherForecast(city: String?) = remoteDataSource.getWeatherForecast(city).map {
        it.list?.toWeatherModelList(it.city)
    }

}