package com.example.weatherforecastapp.presenter.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecastapp.databinding.ItemWeatherBinding
import com.example.weatherforecastapp.domain.models.WeatherModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class ForecastViewHolder(
    private val binding: ItemWeatherBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: WeatherModel) {
        with(binding) {
            pbContentIcon.visibility = View.VISIBLE
            Picasso.get()
                .load(item.icon)
                .into(ivIcon, object : Callback {
                    override fun onSuccess() {
                        pbContentIcon.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        pbContentIcon.visibility = View.GONE
                    }
                })
            tvDegrees.text = item.temp
            tvDate.text = item.date
        }
    }

}