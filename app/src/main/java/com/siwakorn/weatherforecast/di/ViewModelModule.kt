package com.siwakorn.weatherforecast.di

import com.siwakorn.weatherforecast.ui.main.search.ForecastSearchViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { ForecastSearchViewModel(get()) }
}