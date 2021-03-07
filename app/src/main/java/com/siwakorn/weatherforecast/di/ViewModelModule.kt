package com.siwakorn.weatherforecast.di

import com.siwakorn.weatherforecast.ui.weatherforecast.dailyforecast.ForecastDailyViewModel
import com.siwakorn.weatherforecast.ui.weatherforecast.weather.WeatherSearchViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { WeatherSearchViewModel(get(), get()) }
    factory { ForecastDailyViewModel(get(), get()) }
}