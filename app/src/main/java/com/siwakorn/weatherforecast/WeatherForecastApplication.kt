package com.siwakorn.weatherforecast

import android.app.Application
import com.siwakorn.weatherforecast.di.networkModule
import com.siwakorn.weatherforecast.di.serviceModule
import com.siwakorn.weatherforecast.di.useCaseModule
import com.siwakorn.weatherforecast.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherForecastApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WeatherForecastApplication)
            modules(getListModule())
        }
    }

    private fun getListModule() =
        listOf(
            networkModule, serviceModule,
            useCaseModule, viewModelModule
        )

}