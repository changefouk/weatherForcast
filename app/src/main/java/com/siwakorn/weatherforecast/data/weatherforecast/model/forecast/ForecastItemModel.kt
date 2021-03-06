package com.siwakorn.weatherforecast.data.weatherforecast.model.forecast

import com.google.gson.annotations.SerializedName
import com.siwakorn.weatherforecast.data.weatherforecast.model.common.WeatherMainModel
import com.siwakorn.weatherforecast.data.weatherforecast.model.common.WeatherModel
import com.siwakorn.weatherforecast.data.weatherforecast.model.common.mapToDomain
import com.siwakorn.weatherforecast.domain.weatherforecast.forecast.ForecastItem

data class ForecastItemModel(
    @SerializedName("dt") val dateTime: Long,
    @SerializedName("main") val main: WeatherMainModel,
    @SerializedName("weather") val weather: List<WeatherModel>,
    @SerializedName("dt_txt") val dateTimeTxt: String
)

fun ForecastItemModel.mapToDomain() = ForecastItem(
    dateTime = dateTime,
    main = main.mapToDomain(),
    weather = weather[0].mapToDomain(),
    dateTimeTxt = dateTimeTxt
)