package com.siwakorn.weatherforecast.domain.weatherforecast.forecast

import com.siwakorn.weatherforcast.R
import com.siwakorn.weatherforecast.common.resource.ResourceProvider
import com.siwakorn.weatherforecast.domain.weatherforecast.common.WeatherUnit
import com.siwakorn.weatherforecast.ui.weatherforecast.dailyforecast.adapter.ForecastDailyAdapterUiModel
import com.siwakorn.weatherforecast.util.extension.toDisplayTime
import java.time.Instant
import java.time.ZoneId

data class ForecastResponse(
    val city: ForecastCity,
    val forecastList: List<ForecastItem>
) {
    fun mapListForecastUi(
        resourceProvider: ResourceProvider,
        unit: WeatherUnit
    ): List<ForecastDailyAdapterUiModel> {

        val listForecastToday = getListForecastToday()

        val tempResource = if (unit == WeatherUnit.CELSIUS) {
            R.string.temperature_celsius
        } else {
            R.string.temperature_fahrenheit
        }
        return listForecastToday.map {
            ForecastDailyAdapterUiModel(
                time = it.dateTime.toDisplayTime(city.timezone),
                temp = resourceProvider.string(tempResource, it.main.temp),
                humidity = resourceProvider.string(R.string.humidity_percent, it.main.humidity),
                iconUrl = resourceProvider.string(R.string.config_weather_icon_url, it.weather.icon),
            )
        }
    }

    private fun getListForecastToday(): List<ForecastItem> {
        val filterLocalDate =
            Instant.ofEpochSecond(forecastList[0].dateTime, city.timezone)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()

        return forecastList.filter { forecast ->
            filterLocalDate.isEqual(
                Instant.ofEpochSecond(forecast.dateTime, city.timezone)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
            )
        }
    }

}