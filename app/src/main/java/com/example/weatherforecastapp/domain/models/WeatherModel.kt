package com.example.weatherforecastapp.domain.models

import androidx.recyclerview.widget.DiffUtil

data class WeatherModel(
    val id: Int? = null,
    val temp: String? = null,
    val humidity: String? = null,
    val winSpeed: String? = null,
    val description: String? = null,
    val icon: String? = null,
    val date: String? = null,
    val city: String? = null
) {
    companion object {
        val DIFF_ITEM_CALLBACK = object : DiffUtil.ItemCallback<WeatherModel>() {
            override fun areItemsTheSame(
                oldItem: WeatherModel, newItem: WeatherModel
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: WeatherModel, newItem: WeatherModel
            ) = oldItem == newItem
        }
    }
}