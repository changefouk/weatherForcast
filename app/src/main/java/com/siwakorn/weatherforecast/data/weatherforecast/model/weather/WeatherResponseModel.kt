package com.siwakorn.weatherforecast.data.weatherforecast.model.weather

import com.google.gson.annotations.SerializedName
import com.siwakorn.weatherforecast.common.resource.ResourceProvider
import com.siwakorn.weatherforecast.data.base.BaseResponse
import com.siwakorn.weatherforecast.data.weatherforecast.model.common.CoordModel
import com.siwakorn.weatherforecast.data.weatherforecast.model.common.WeatherModel
import com.siwakorn.weatherforecast.data.weatherforecast.model.common.mapToDomain
import com.siwakorn.weatherforecast.domain.weatherforecast.weather.WeatherResponse

data class WeatherResponseModel(
    @SerializedName("coord") val coord: CoordModel,
    @SerializedName("weather") val weather: List<WeatherModel>
) : BaseResponse()

fun WeatherResponseModel.mapToDomain(resourceProvider: ResourceProvider) = WeatherResponse(
    coord = coord.mapToDomain(),
    weather = weather[0].mapToDomain(resourceProvider)
)