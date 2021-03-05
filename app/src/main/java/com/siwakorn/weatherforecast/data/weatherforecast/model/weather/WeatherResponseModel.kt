package com.siwakorn.weatherforecast.data.weatherforecast.model.weather

import com.google.gson.annotations.SerializedName
import com.siwakorn.weatherforecast.data.base.BaseResponse

data class WeatherResponseModel(
    @SerializedName("coord") val coord: Coord = Coord()
) : BaseResponse()


data class Coord(
    @SerializedName("lat") val latitude: Double = 0.0,
    @SerializedName("lon") val longitude: Double = 0.0
)