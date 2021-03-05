package com.siwakorn.weatherforecast.data.weatherforecast.model.weather

import com.google.gson.annotations.SerializedName
import com.siwakorn.weatherforecast.data.base.BaseResponse
import com.siwakorn.weatherforecast.data.weatherforecast.model.common.Coord

data class WeatherResponseModel(
    @SerializedName("coord") val coord: Coord = Coord()
) : BaseResponse()