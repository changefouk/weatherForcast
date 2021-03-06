package com.siwakorn.weatherforecast.data.weatherforecast.model.forecast

import com.google.gson.annotations.SerializedName
import com.siwakorn.weatherforecast.data.weatherforecast.model.common.CoordModel
import com.siwakorn.weatherforecast.data.weatherforecast.model.common.mapToDomain
import com.siwakorn.weatherforecast.domain.weatherforecast.forecast.ForecastCity

data class ForecastCityModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("coord") val coord: CoordModel,
    @SerializedName("timezone") val timezone: Long
)

fun ForecastCityModel.mapToDomain() = ForecastCity(
    id = id,
    name = name,
    coord = coord.mapToDomain(),
    timezone = timezone
)