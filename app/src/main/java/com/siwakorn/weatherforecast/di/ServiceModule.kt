package com.siwakorn.weatherforecast.di

import com.siwakorn.weatherforecast.data.weatherforecast.WeatherForecastApi
import com.siwakorn.weatherforecast.data.weatherforecast.WeatherForecastRepository
import com.siwakorn.weatherforecast.data.weatherforecast.WeatherForecastRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceModule = module {
    single { get<Retrofit>().create(WeatherForecastApi::class.java) }
}

val repositoryModule = module {
    factory<WeatherForecastRepository> { WeatherForecastRepositoryImpl(get(), get()) }
}