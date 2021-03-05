package com.siwakorn.weatherforecast.data.weatherforecast.model.common

import com.google.gson.annotations.SerializedName
import com.siwakorn.weatherforecast.domain.weatherforecast.common.Coord

data class CoordModel(
    @SerializedName("lat") val latitude: Double = 0.0,
    @SerializedName("lon") val longitude: Double = 0.0
)

fun CoordModel.mapToDomain() = Coord(
    latitude = latitude,
    longitude = longitude
)