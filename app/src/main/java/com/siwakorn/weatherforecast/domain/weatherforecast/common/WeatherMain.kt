package com.siwakorn.weatherforecast.domain.weatherforecast.common

import com.google.gson.annotations.SerializedName

data class WeatherMain(
    val temp: Double,
    val feelsLike: Double,
    val tempMin: Double,
    val tempMax: Double,
    val pressure: Int,
    val humidity: Int
)