package com.siwakorn.weatherforecast.util.extension

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Long.toDisplayDayMonthDate(timeZone: Long): String {
    val localDate =
        Instant.ofEpochSecond(this, timeZone).atZone(ZoneId.systemDefault())
            .toLocalDate()
    return localDate.format(DateTimeFormatter.ofPattern("EEEE, MMM dd"))
}

fun Long.toDisplayTime(timeZone: Long): String {
    val localTime =
        Instant.ofEpochSecond(this, timeZone).atZone(ZoneId.systemDefault()).toLocalDateTime()
    return localTime.format(DateTimeFormatter.ofPattern("HH:mm"))
}