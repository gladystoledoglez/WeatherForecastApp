package com.example.weatherforecastapp.data.entities

import com.google.gson.annotations.SerializedName

data class ForecastEntity(
    @SerializedName("cod") val cod: String?,
    @SerializedName("message") val message: Int?,
    @SerializedName("cnt") val cnt: Int?,
    @SerializedName("list") val list: List<ItemForecastEntity>?,
    @SerializedName("city") val city: CityEntity?
)