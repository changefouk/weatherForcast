package com.siwakorn.weatherforecast.data.weatherforecast.model.forecast

import com.google.gson.annotations.SerializedName
import com.siwakorn.weatherforecast.data.base.BaseResponse

data class ForecastResponseModel(
    @SerializedName("city") val city: ForecastCityModel,
    @SerializedName("list") val forecastList: List<ForecastItemModel>
) : BaseResponse()
