package com.example.weatherforecastapp

import android.app.Application
import com.example.weatherforecastapp.di.MainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WeatherApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@WeatherApp)
        }
        MainModule.init()
    }
}