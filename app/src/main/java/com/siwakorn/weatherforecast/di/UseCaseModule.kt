package com.siwakorn.weatherforecast.di

import com.siwakorn.weatherforecast.domain.weatherforecast.weather.GetWeatherUseCase
import com.siwakorn.weatherforecast.domain.weatherforecast.weather.GetWeatherUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetWeatherUseCase> { GetWeatherUseCaseImpl(get()) }
}