package com.siwakorn.weatherforecast.data.weatherforecast.model.forecast

import com.google.gson.annotations.SerializedName
import com.siwakorn.weatherforecast.data.base.BaseResponse
import com.siwakorn.weatherforecast.domain.weatherforecast.forecast.ForecastResponse

data class ForecastResponseModel(
    @SerializedName("city") val city: ForecastCityModel,
    @SerializedName("list") val forecastList: List<ForecastItemModel>
) : BaseResponse()

fun ForecastResponseModel.mapToDomain() = ForecastResponse(
    city = city.mapToDomain(),
    forecastList = forecastList.map { it.mapToDomain() }
)
