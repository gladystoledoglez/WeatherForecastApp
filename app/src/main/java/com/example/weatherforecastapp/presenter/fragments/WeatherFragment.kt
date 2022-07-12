package com.example.weatherforecastapp.presenter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.weatherforecastapp.databinding.FragmentWeatherBinding
import com.example.weatherforecastapp.domain.models.WeatherModel
import com.example.weatherforecastapp.extensions.orFalse
import com.example.weatherforecastapp.presenter.adapters.ForecastListAdapter
import com.example.weatherforecastapp.presenter.viewModels.WeatherViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherFragment : Fragment() {

    private lateinit var binding: FragmentWeatherBinding
    private val pbHeader by lazy { binding.pbHeader }
    private val pbContent by lazy { binding.pbContent }
    private val rvWeather by lazy { binding.rvWeather }
    private val viewModel: WeatherViewModel by viewModel(clazz = WeatherViewModel::class.java.kotlin)
    private val adapter = ForecastListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentWeatherBinding.inflate(layoutInflater)

        initComponents()
        initObservers()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        rvWeather.adapter = null
    }

    private fun initComponents() {
        with(binding) {
            rvWeather.adapter = adapter
            svLocation.setOnQueryTextListener(
                object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(queryText: String?): Boolean {
                        viewModel.getForecast(queryText)
                        return false
                    }

                    override fun onQueryTextChange(queryText: String?): Boolean {
                        if (queryText?.isBlank() == true) {
                            rvWeather.isVisible = false
                            lytHeader.root.isVisible = false
                        }
                        return false
                    }
                }
            )
        }
    }

    private fun initObservers() {
        with(viewModel) {
            isHeaderLoading.observe(viewLifecycleOwner) { pbHeader.isVisible = it.orFalse() }
            isContentLoading.observe(viewLifecycleOwner) { pbContent.isVisible = it.orFalse() }
            summary.observe(viewLifecycleOwner) { setupHeader(it) }
            weathers.observe(viewLifecycleOwner) {
                adapter.submitList(it)
                rvWeather.isVisible = true
            }
            message.observe(viewLifecycleOwner) {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setupHeader(weather: WeatherModel?) {
        binding.lytHeader.apply {
            root.isVisible = weather?.let {
                tvCity.text = viewModel.city.value
                tvDate.text = it.date
                pbHeaderIcon.visibility = View.VISIBLE
                Picasso.get()
                    .load(it.icon)
                    .into(ivIcon, object : Callback {
                        override fun onSuccess() {
                            pbHeaderIcon.visibility = View.GONE
                        }

                        override fun onError(e: Exception?) {
                            pbHeaderIcon.visibility = View.GONE
                        }
                    })

                tvTemperature.text = it.temp.toString()
                tvHumidityValue.text = it.humidity.toString()
                tvWindSpeedValue.text = it.winSpeed.toString()
                tvWeatherState.text = it.description
                true
            } ?: false
        }
    }

    companion object {
        fun newInstance() = WeatherFragment()
    }
}