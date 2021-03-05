package com.siwakorn.weatherforecast.common.config

import com.siwakorn.weatherforcast.BuildConfig

class ConfigProviderImpl : ConfigProvider {

    override val baseUrl: String
        get() = BuildConfig.BASE_URL

    override val apiKey: String
        get() = BuildConfig.API_KEY

}