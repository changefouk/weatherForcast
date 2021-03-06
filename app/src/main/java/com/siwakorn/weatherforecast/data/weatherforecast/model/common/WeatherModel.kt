package com.siwakorn.weatherforecast.data.weatherforecast.model.common

import com.google.gson.annotations.SerializedName
import com.siwakorn.weatherforcast.R
import com.siwakorn.weatherforecast.common.resource.ResourceProvider
import com.siwakorn.weatherforecast.domain.weatherforecast.common.Weather

/**
 * How to get icon URL
 * For code 500 - light rain icon = "10d". See below a full list of codes
 * URL is http://openweathermap.org/img/wn/10d@2x.png
 * source : https://openweathermap.org/weather-conditions#Weather-Condition-Codes-2
 * */

data class WeatherModel(
    @SerializedName("id") val id: Int,
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)

fun WeatherModel.mapToDomain() = Weather(
    id = id,
    main = main,
    description = description,
    icon = icon
)