package com.siwakorn.weatherforecast.domain.weatherforecast.forecast

import com.siwakorn.weatherforecast.domain.weatherforecast.common.Weather
import com.siwakorn.weatherforecast.domain.weatherforecast.common.WeatherMain

data class ForecastItem(
    val dateTime: Long,
    val main: WeatherMain,
    val weather: List<Weather>,
    val dateTimeTxt: String
)