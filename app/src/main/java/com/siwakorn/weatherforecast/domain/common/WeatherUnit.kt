package com.siwakorn.weatherforecast.domain.common


/**
 * Temperature is available in Fahrenheit, Celsius and Kelvin units.
 *
 * For temperature in Fahrenheit use units=imperial
 * For temperature in Celsius use units=metric
 * Temperature in Kelvin is used by default, no need to use units parameter in API call
 *
 *  ref:  https://openweathermap.org/current#data
 * */

enum class WeatherUnit(val unit: String) {
    CELSIUS(unit = "metric"),
    FAHRENHEIT(unit = "imperial");

    companion object {
        @JvmStatic
        fun displayUnit(source: WeatherUnit?): String {
            return when (source) {
                CELSIUS -> "°C"
                FAHRENHEIT -> "°F"
                else -> "°C"
            }
        }
    }
}