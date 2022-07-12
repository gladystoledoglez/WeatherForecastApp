package com.example.weatherforecastapp.presenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.weatherforecastapp.databinding.ItemWeatherBinding
import com.example.weatherforecastapp.domain.models.WeatherModel
import com.example.weatherforecastapp.domain.models.WeatherModel.Companion.DIFF_ITEM_CALLBACK
import com.example.weatherforecastapp.presenter.viewHolders.ForecastViewHolder

class ForecastListAdapter : ListAdapter<WeatherModel, ForecastViewHolder>(DIFF_ITEM_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ForecastViewHolder(
        ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}