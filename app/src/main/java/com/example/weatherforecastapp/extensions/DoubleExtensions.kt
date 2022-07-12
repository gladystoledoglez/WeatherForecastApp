package com.example.weatherforecastapp.extensions

import kotlin.math.roundToInt

const val CELSIUS_DEGREES = 273.15
const val KILOMETERS_FOR_HOUR = 3.6

fun Double.toKilometersForHour() = "${(this * KILOMETERS_FOR_HOUR).roundToInt()} km/h"

fun Double.toCelsius() = "${(this - CELSIUS_DEGREES).roundToInt()}°"