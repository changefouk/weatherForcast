package com.siwakorn.weatherforecast.domain.weatherforecast.forecast

import com.siwakorn.weatherforcast.R
import com.siwakorn.weatherforecast.common.resource.ResourceProvider
import com.siwakorn.weatherforecast.domain.weatherforecast.common.WeatherUnit
import com.siwakorn.weatherforecast.ui.weatherforecast.daily.adapter.ForecastDailyUi
import com.siwakorn.weatherforecast.util.extension.toDisplayTime

data class ForecastResponse(
    val city: ForecastCity,
    val forecastList: List<ForecastItem>
) {
    fun mapListForecastUi(
        resourceProvider: ResourceProvider,
        unit: WeatherUnit
    ): List<ForecastDailyUi> {
        val tempResource = if (unit == WeatherUnit.CELSIUS) {
            R.string.temperature_celsius
        } else {
            R.string.temperature_fahrenheit
        }
        return forecastList.map {
            ForecastDailyUi(
                time = it.dateTime.toDisplayTime(city.timezone),
                temp = resourceProvider.string(tempResource, it.main.temp),
                humidity = resourceProvider.string(R.string.humidity_percent, it.main.humidity),
                iconUrl =  resourceProvider.string(R.string.config_weather_icon_url, it.weather.icon),
            )
        }
    }

}