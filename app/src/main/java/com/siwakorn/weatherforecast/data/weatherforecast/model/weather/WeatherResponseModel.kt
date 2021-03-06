package com.siwakorn.weatherforecast.data.weatherforecast.model.weather

import com.google.gson.annotations.SerializedName
import com.siwakorn.weatherforecast.common.resource.ResourceProvider
import com.siwakorn.weatherforecast.data.base.BaseResponse
import com.siwakorn.weatherforecast.data.weatherforecast.model.common.CoordModel
import com.siwakorn.weatherforecast.data.weatherforecast.model.common.WeatherMainModel
import com.siwakorn.weatherforecast.data.weatherforecast.model.common.WeatherModel
import com.siwakorn.weatherforecast.data.weatherforecast.model.common.mapToDomain
import com.siwakorn.weatherforecast.domain.weatherforecast.weather.WeatherResponse

data class WeatherResponseModel(
    @SerializedName("coord") val coord: CoordModel,
    @SerializedName("weather") val weather: List<WeatherModel>,
    @SerializedName("main") val main: WeatherMainModel,
    @SerializedName("name") val cityName: String,
    @SerializedName("dt") val dateTime: Long,
    @SerializedName("timezone") val timeZone: Long
) : BaseResponse()

fun WeatherResponseModel.mapToDomain() = WeatherResponse(
    coord = coord.mapToDomain(),
    weather = weather[0].mapToDomain(),
    weatherMain = main.mapToDomain(),
    cityName = cityName,
    dateTime = dateTime,
    timeZone = timeZone
)