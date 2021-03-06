package com.siwakorn.weatherforecast.domain.weatherforecast.forecast

import com.siwakorn.weatherforecast.domain.weatherforecast.common.WeatherUnit

data class GetForecastBody(
    val cityName: String,
    val unit: String
)