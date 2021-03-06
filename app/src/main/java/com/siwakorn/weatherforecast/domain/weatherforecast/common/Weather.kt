package com.siwakorn.weatherforecast.domain.weatherforecast.common

/**
 * How to get icon URL
 * For code 500 - light rain icon = "10d". See below a full list of codes
 * URL is http://openweathermap.org/img/wn/10d@2x.png
 * source : https://openweathermap.org/weather-conditions#Weather-Condition-Codes-2
 * */

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val iconUrl: String
)