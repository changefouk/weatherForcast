package com.siwakorn.weatherforecast.util.extension

fun Int.isSuccess(): Boolean {
    return this == 200
}