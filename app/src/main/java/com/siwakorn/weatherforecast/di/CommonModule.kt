package com.siwakorn.weatherforecast.di

import com.siwakorn.weatherforecast.common.config.ConfigProvider
import com.siwakorn.weatherforecast.common.config.ConfigProviderImpl
import org.koin.dsl.module

val commonModule = module {
    single<ConfigProvider> { ConfigProviderImpl() }
}