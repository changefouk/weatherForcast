package com.siwakorn.weatherforecast.data.weatherforecast

import com.siwakorn.weatherforecast.common.resource.ResourceProvider
import com.siwakorn.weatherforecast.data.base.BaseService
import com.siwakorn.weatherforecast.data.weatherforecast.model.weather.WeatherResponseModel
import com.siwakorn.weatherforecast.data.weatherforecast.model.weather.mapToDomain
import com.siwakorn.weatherforecast.domain.weatherforecast.weather.GetWeatherForecastBody
import com.siwakorn.weatherforecast.domain.weatherforecast.weather.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface WeatherForecastRepository {
    fun getWeather(request: GetWeatherForecastBody): Flow<WeatherResponse>
}

class WeatherForecastRepositoryImpl
constructor(
    private val api: WeatherForecastApi,
    private val resourceProvider: ResourceProvider
) : WeatherForecastRepository {

    override fun getWeather(request: GetWeatherForecastBody): Flow<WeatherResponse> =
        object : BaseService<WeatherResponseModel, WeatherResponse>() {

            override suspend fun callApi(): WeatherResponseModel =
                api.getWeather(request.latitude, request.longitude, request.unit)

            override fun mapper(from: WeatherResponseModel): WeatherResponse =
                from.mapToDomain(resourceProvider)

        }.execute()

}