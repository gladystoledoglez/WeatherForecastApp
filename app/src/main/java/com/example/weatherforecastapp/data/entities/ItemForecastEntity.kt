package com.example.weatherforecastapp.data.entities

import com.google.gson.annotations.SerializedName

data class ItemForecastEntity(
    @SerializedName("dt") val dt: Long?,
    @SerializedName("main") val statistics: StatisticsEntity?,
    @SerializedName("weather") val weather: List<WeatherEntity>?,
    @SerializedName("clouds") val clouds: CloudsEntity?,
    @SerializedName("wind") val wind: WindEntity?,
    @SerializedName("visibility") val visibility: Int?,
    @SerializedName("pop") val pop: Double?,
    @SerializedName("sys") val sys: SysEntity?,
    @SerializedName("dt_txt") val dt_txt: String?
)