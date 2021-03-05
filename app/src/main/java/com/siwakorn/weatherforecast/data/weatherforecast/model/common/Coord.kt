package com.siwakorn.weatherforecast.data.weatherforecast.model.common

import com.google.gson.annotations.SerializedName

data class Coord(
    @SerializedName("lat") val latitude: Double = 0.0,
    @SerializedName("lon") val longitude: Double = 0.0
)