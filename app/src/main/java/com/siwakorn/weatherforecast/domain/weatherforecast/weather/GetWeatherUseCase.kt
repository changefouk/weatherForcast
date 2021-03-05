package com.siwakorn.weatherforecast.domain.weatherforecast.weather

import com.siwakorn.weatherforecast.data.weatherforecast.WeatherForecastRepository
import kotlinx.coroutines.flow.Flow

interface GetWeatherUseCase {
    fun execute(request: GetWeatherForecastBody): Flow<WeatherResponse>
}

class GetWeatherUseCaseImpl(
    private val repository: WeatherForecastRepository
) : GetWeatherUseCase {

    override fun execute(request: GetWeatherForecastBody) = repository.getWeather(request)

}