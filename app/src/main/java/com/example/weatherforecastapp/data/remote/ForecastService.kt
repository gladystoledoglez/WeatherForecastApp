package com.example.weatherforecastapp.data.remote

import com.example.weatherforecastapp.data.entities.ForecastEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastService {

    @GET("forecast/")
    suspend fun getWeatherForecast(
        @Query("q") city: String? = "London,uk",
        @Query("APPID") apiKey: String = API_KEY
    ): ForecastEntity

    companion object {
        const val API_KEY = "2f96476e586c41bb68a58680451c0294"
    }
}