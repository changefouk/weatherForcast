package com.siwakorn.weatherforecast.domain.weatherforecast.weather

import com.siwakorn.weatherforecast.domain.weatherforecast.common.Coord
import com.siwakorn.weatherforecast.domain.weatherforecast.common.Weather
import com.siwakorn.weatherforecast.domain.weatherforecast.common.WeatherMain
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class WeatherResponse(
    val coord: Coord,
    val weather: Weather,
    val weatherMain: WeatherMain,
    val cityName: String,
    val dateTime: Long,
    val timeZone: Long
) {

    fun getDisplayDateTime(): String {
        val localTime =
            Instant.ofEpochSecond(dateTime, timeZone).atZone(ZoneId.systemDefault())
                .toLocalDateTime()
        return localTime.format(DateTimeFormatter.ofPattern("EEEE, MMM dd HH:mm"))
    }

}