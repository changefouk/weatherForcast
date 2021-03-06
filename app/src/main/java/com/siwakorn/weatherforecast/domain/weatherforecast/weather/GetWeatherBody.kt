package com.siwakorn.weatherforecast.domain.weatherforecast.weather

data class GetWeatherBody(
    val latitude: Double,
    val longitude: Double,
    val unit: String
)