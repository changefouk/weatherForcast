package com.siwakorn.weatherforecast.domain.weatherforecast.weather

data class GetWeatherForecastBody(
    val latitude: Double,
    val longitude: Double,
    val unit: String
)