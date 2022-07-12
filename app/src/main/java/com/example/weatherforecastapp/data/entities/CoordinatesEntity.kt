package com.example.weatherforecastapp.data.entities

import com.google.gson.annotations.SerializedName

data class CoordinatesEntity(
    @SerializedName("lat") val latitude: Double?,
    @SerializedName("lon") val longitude: Double?
)