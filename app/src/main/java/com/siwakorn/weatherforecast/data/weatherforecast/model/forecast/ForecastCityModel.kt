package com.siwakorn.weatherforecast.data.weatherforecast.model.forecast

import com.google.gson.annotations.SerializedName
import com.siwakorn.weatherforecast.data.weatherforecast.model.common.CoordModel

data class ForecastCityModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("coord") val coord: CoordModel,
)