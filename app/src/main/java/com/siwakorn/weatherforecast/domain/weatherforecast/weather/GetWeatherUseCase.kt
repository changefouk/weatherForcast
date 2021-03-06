package com.siwakorn.weatherforecast.domain.weatherforecast.weather

import com.siwakorn.weatherforecast.data.weatherforecast.WeatherForecastRepository
import kotlinx.coroutines.flow.Flow

interface GetWeatherUseCase {
    fun execute(request: GetWeatherBody): Flow<WeatherResponse>
}

class GetWeatherUseCaseImpl constructor(
    private val repository: WeatherForecastRepository
) : GetWeatherUseCase {

    override fun execute(request: GetWeatherBody) = repository.getWeather(request)

}