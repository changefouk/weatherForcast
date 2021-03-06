package com.siwakorn.weatherforecast.data.weatherforecast

import com.siwakorn.weatherforecast.data.base.BaseService
import com.siwakorn.weatherforecast.data.weatherforecast.model.forecast.ForecastResponseModel
import com.siwakorn.weatherforecast.data.weatherforecast.model.forecast.mapToDomain
import com.siwakorn.weatherforecast.data.weatherforecast.model.weather.WeatherResponseModel
import com.siwakorn.weatherforecast.data.weatherforecast.model.weather.mapToDomain
import com.siwakorn.weatherforecast.domain.weatherforecast.forecast.ForecastResponse
import com.siwakorn.weatherforecast.domain.weatherforecast.forecast.GetForecastBody
import com.siwakorn.weatherforecast.domain.weatherforecast.weather.GetWeatherBody
import com.siwakorn.weatherforecast.domain.weatherforecast.weather.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface WeatherForecastRepository {
    fun getWeather(request: GetWeatherBody): Flow<WeatherResponse>
    fun getForecast(request: GetForecastBody): Flow<ForecastResponse>
}

class WeatherForecastRepositoryImpl
constructor(private val api: WeatherForecastApi) : WeatherForecastRepository {

    override fun getWeather(request: GetWeatherBody): Flow<WeatherResponse> =
        object : BaseService<WeatherResponseModel, WeatherResponse>() {

            override suspend fun callApi(): WeatherResponseModel =
                api.getWeather(request.latitude, request.longitude, request.unit)

            override fun mapper(from: WeatherResponseModel): WeatherResponse =
                from.mapToDomain()

        }.execute()

    override fun getForecast(request: GetForecastBody): Flow<ForecastResponse> =
        object : BaseService<ForecastResponseModel, ForecastResponse>() {
            override suspend fun callApi(): ForecastResponseModel =
                api.getForecast(request.cityName, request.unit, request.cnt)

            override fun mapper(from: ForecastResponseModel): ForecastResponse =
                from.mapToDomain()

        }.execute()

}