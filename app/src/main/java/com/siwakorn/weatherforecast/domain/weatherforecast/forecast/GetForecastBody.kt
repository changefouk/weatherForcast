package com.siwakorn.weatherforecast.domain.weatherforecast.forecast

data class GetForecastBody(
    val cityName: String,
    val unit: String,
    val cnt: Int = 8
)