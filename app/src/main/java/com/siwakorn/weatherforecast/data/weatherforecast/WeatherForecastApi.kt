package com.siwakorn.weatherforecast.data.weatherforecast

import com.siwakorn.weatherforecast.constant.ApiConstant
import com.siwakorn.weatherforecast.data.weatherforecast.model.forecast.ForecastResponseModel
import com.siwakorn.weatherforecast.data.weatherforecast.model.weather.WeatherResponseModel
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherForecastApi {

    @GET(ApiConstant.EndPoint.WEATHER)
    suspend fun getWeather(
        @Query(ApiConstant.QueryParam.Q) cityName: String,
        @Query(ApiConstant.QueryParam.UNITS) unit: String
    ): WeatherResponseModel


    @GET(ApiConstant.EndPoint.WEATHER)
    suspend fun getWeather(
        @Query(ApiConstant.QueryParam.LATITUDE) latitude: Double,
        @Query(ApiConstant.QueryParam.LONGITUDE) longitude: Double,
        @Query(ApiConstant.QueryParam.UNITS) unit: String
    ): WeatherResponseModel

    @GET(ApiConstant.EndPoint.FORECAST)
    suspend fun getForecast(
        @Query(ApiConstant.QueryParam.Q) cityName: String,
        @Query(ApiConstant.QueryParam.UNITS) unit: String,
        @Query(ApiConstant.QueryParam.CNT) countTime: Int
    ): ForecastResponseModel

}