package com.example.weatherforecastapp.data.mappers

import com.example.weatherforecastapp.data.entities.CityEntity
import com.example.weatherforecastapp.data.entities.ItemForecastEntity
import com.example.weatherforecastapp.domain.models.WeatherModel
import com.example.weatherforecastapp.extensions.*

fun ItemForecastEntity.toWeatherModel(city: CityEntity?): WeatherModel {
    val first = weather?.firstOrNull()
    return WeatherModel(
        id = first?.id.orZero(),
        humidity = "${statistics?.humidity}%",
        temp = statistics?.temp?.toCelsius(),
        winSpeed = wind?.speed?.toKilometersForHour(),
        description = first?.description,
        icon = first?.icon?.getIconUrl().orEmpty(),
        date = dt_txt?.toDateFormat()?.toDateFormatStr(),
        city = "${city?.name.orEmpty()}, ${city?.country}"
    )
}

fun List<ItemForecastEntity>.toWeatherModelList(city: CityEntity?) = map { it.toWeatherModel(city) }