package com.siwakorn.weatherforecast.di

import com.siwakorn.weatherforecast.common.config.ConfigProvider
import com.siwakorn.weatherforecast.common.config.ConfigProviderImpl
import com.siwakorn.weatherforecast.common.resource.ResourceProvider
import com.siwakorn.weatherforecast.common.resource.ResourceProviderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val commonModule = module {
    single<ConfigProvider> { ConfigProviderImpl() }
    single<ResourceProvider> { ResourceProviderImpl(androidContext()) }
}