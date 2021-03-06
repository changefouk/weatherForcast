package com.siwakorn.weatherforecast.data.weatherforecast.model.common

import com.google.gson.annotations.SerializedName
import com.siwakorn.weatherforecast.domain.weatherforecast.common.WeatherMain

data class WeatherMainModel(
    @SerializedName("temp") val temp: Double,
    @SerializedName("feels_like") val feels_like: Double,
    @SerializedName("temp_min") val temp_min: Double,
    @SerializedName("temp_max") val temp_max: Double,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int
)

fun WeatherMainModel.mapToDomain() = WeatherMain(
    temp = temp,
    feelsLike = feels_like,
    tempMin = temp_min,
    tempMax = temp_max,
    pressure = pressure,
    humidity = humidity
)