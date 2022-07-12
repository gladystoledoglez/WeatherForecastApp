package com.example.weatherforecastapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherforecastapp.databinding.ActivityMainBinding
import com.example.weatherforecastapp.extensions.transitionTo
import com.example.weatherforecastapp.presenter.fragments.WeatherFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.transitionTo(WeatherFragment.newInstance(), isBackStack = false)
    }
}