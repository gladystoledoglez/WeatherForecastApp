package com.example.weatherforecastapp.extensions

import com.example.weatherforecastapp.data.entities.CityEntity

fun CityEntity.toLocationFormat() = "$name, $country"