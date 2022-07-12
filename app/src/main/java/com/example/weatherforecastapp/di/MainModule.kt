package com.example.weatherforecastapp.di

import com.example.weatherforecastapp.data.repository.WeatherDataRepository
import com.example.weatherforecastapp.data.remote.ForecastService
import com.example.weatherforecastapp.data.remote.WeatherRemoteDataSource
import com.example.weatherforecastapp.domain.repository.WeatherRepository
import com.example.weatherforecastapp.presenter.viewModels.WeatherViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun Scope.getRetrofit() = get<Retrofit>()

object MainModule {
    private val networkModule = module {

        fun provideBaseUrl() = "https://api.openweathermap.org/data/2.5/"

        fun provideGson() = GsonBuilder().create()

        fun provideOkHttp() = OkHttpClient.Builder().build()

        fun provideRetrofit(baseUrl: String, okHttp: OkHttpClient, gson: Gson) = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        single { provideBaseUrl() }
        single { provideGson() }
        single { provideOkHttp() }
        single { provideRetrofit(get(), get(), get()) }
        single { getRetrofit().create(ForecastService::class.java) }
    }

    private val dataModule = module {
        single { WeatherRemoteDataSource(get()) }
        single<WeatherRepository> { WeatherDataRepository(get()) }
    }

    private val viewModelsModule = module {
        viewModel { WeatherViewModel(get()) }
    }

    fun init() = loadKoinModules(listOf(networkModule, dataModule, viewModelsModule))
}