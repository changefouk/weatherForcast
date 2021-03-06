package com.siwakorn.weatherforecast.di

import com.siwakorn.weatherforecast.domain.weatherforecast.forecast.GetForecastUseCase
import com.siwakorn.weatherforecast.domain.weatherforecast.forecast.GetForecastUseCaseImpl
import com.siwakorn.weatherforecast.domain.weatherforecast.weather.GetWeatherUseCase
import com.siwakorn.weatherforecast.domain.weatherforecast.weather.GetWeatherUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetWeatherUseCase> { GetWeatherUseCaseImpl(get()) }
    factory<GetForecastUseCase> { GetForecastUseCaseImpl(get()) }
}