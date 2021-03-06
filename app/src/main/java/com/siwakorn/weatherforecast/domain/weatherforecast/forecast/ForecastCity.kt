package com.siwakorn.weatherforecast.domain.weatherforecast.forecast

import com.siwakorn.weatherforecast.domain.weatherforecast.common.Coord

data class ForecastCity(
    val id: Int,
    val name: String,
    val coord: Coord,
    val timezone: Long
)