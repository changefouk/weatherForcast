package com.siwakorn.weatherforecast.domain.weatherforecast.weather

import com.siwakorn.weatherforecast.domain.weatherforecast.common.Coord
import com.siwakorn.weatherforecast.domain.weatherforecast.common.Weather

data class WeatherResponse(
    val coord: Coord,
    val weather: Weather
)