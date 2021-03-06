package com.siwakorn.weatherforecast.domain.weatherforecast.forecast

import com.siwakorn.weatherforecast.data.weatherforecast.WeatherForecastRepository
import kotlinx.coroutines.flow.Flow


interface GetForecastUseCase {
    fun execute(request: GetForecastBody): Flow<ForecastResponse>
}

class GetForecastUseCaseImpl constructor(
    private val repository: WeatherForecastRepository
): GetForecastUseCase {
    override fun execute(request: GetForecastBody): Flow<ForecastResponse> =
        repository.getForecast(request)
}