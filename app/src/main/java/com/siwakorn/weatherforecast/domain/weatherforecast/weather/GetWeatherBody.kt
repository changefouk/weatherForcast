package com.siwakorn.weatherforecast.domain.weatherforecast.weather

data class GetWeatherBody(
    val queryCityName: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val unit: String
)