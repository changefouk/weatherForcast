package com.siwakorn.weatherforecast.domain.weatherforecast.forecast

data class ForecastResponse(
    val city: ForecastCity,
    val forecastList: List<ForecastItem>
)