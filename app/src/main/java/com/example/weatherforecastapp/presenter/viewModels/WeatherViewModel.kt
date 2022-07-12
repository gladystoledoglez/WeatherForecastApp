package com.example.weatherforecastapp.presenter.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecastapp.domain.models.WeatherModel
import com.example.weatherforecastapp.domain.repository.WeatherRepository
import com.example.weatherforecastapp.extensions.orZero
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {

    private val _city = MutableLiveData<String>()
    val city: LiveData<String> = _city

    private val _summary = MutableLiveData<WeatherModel?>()
    val summary: LiveData<WeatherModel?> = _summary

    private val _weathers = MutableLiveData<List<WeatherModel>>()
    val weathers: LiveData<List<WeatherModel>> = _weathers

    private val _isHeaderLoading = MutableLiveData(false)
    val isHeaderLoading: LiveData<Boolean?> = _isHeaderLoading

    private val _isContentLoading = MutableLiveData(false)
    val isContentLoading: LiveData<Boolean?> = _isContentLoading

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private fun setLoading(isLoading: Boolean = true) {
        _isHeaderLoading.postValue(isLoading)
        _isContentLoading.postValue(isLoading)
    }

    private fun stopLoading() {
        setLoading(false)
    }

    fun getForecast(cityName: String?) {
        if (cityName?.isBlank() == true)
            _message.postValue("Es necesario escribir alguna cosa para buscar")
        else
            viewModelScope.launch {
                repository.getWeatherForecast(cityName.orEmpty())
                    .onStart { setLoading() }
                    .catch {
                        _message.postValue(it.message)
                        stopLoading()
                    }
                    .onCompletion {
                        stopLoading()
                    }
                    .collect {
                        val summary = it?.firstOrNull()
                        val weathers = it?.filter { item -> item.id != summary?.id.orZero() }
                        _city.postValue(summary?.city.orEmpty())
                        _summary.postValue(summary)
                        _weathers.postValue(weathers ?: emptyList())
                    }
            }
    }
}