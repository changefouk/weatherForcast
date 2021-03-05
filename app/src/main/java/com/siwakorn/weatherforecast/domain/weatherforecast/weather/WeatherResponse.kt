package com.siwakorn.weatherforecast.domain.weatherforecast.weather

import com.google.gson.annotations.SerializedName
import com.siwakorn.weatherforecast.domain.weatherforecast.common.Coord

data class WeatherResponse(
    @SerializedName("coord") val coord: Coord
)