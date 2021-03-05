package com.siwakorn.weatherforecast.data.weatherforecast.model.weather

import com.google.gson.annotations.SerializedName
import com.siwakorn.weatherforecast.data.base.BaseResponse
import com.siwakorn.weatherforecast.data.weatherforecast.model.common.CoordModel
import com.siwakorn.weatherforecast.data.weatherforecast.model.common.mapToDomain
import com.siwakorn.weatherforecast.domain.weatherforecast.weather.WeatherResponse

data class WeatherResponseModel(
    @SerializedName("coord") val coord: CoordModel = CoordModel()
) : BaseResponse()

fun WeatherResponseModel.mapToDomain() = WeatherResponse(
    coord = coord.mapToDomain()
)