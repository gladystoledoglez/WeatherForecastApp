package com.example.weatherforecastapp.data.entities

import com.google.gson.annotations.SerializedName

data class WindEntity(
    @SerializedName("speed") val speed: Double?,
    @SerializedName("deg") val degrees: Int?,
    @SerializedName("gust") val gust: Double?
)