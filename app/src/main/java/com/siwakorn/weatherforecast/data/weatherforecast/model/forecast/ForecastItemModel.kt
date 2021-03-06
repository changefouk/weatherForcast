package com.siwakorn.weatherforecast.data.weatherforecast.model.forecast

import com.google.gson.annotations.SerializedName
import com.siwakorn.weatherforecast.data.weatherforecast.model.common.WeatherMainModel
import com.siwakorn.weatherforecast.data.weatherforecast.model.common.WeatherModel

data class ForecastItemModel(
    @SerializedName("dt") val dateTime: Long,
    @SerializedName("main") val main: WeatherMainModel,
    @SerializedName("weather") val weather: List<WeatherModel>,
    @SerializedName("dt_txt") val dateTimeTxt: String
)