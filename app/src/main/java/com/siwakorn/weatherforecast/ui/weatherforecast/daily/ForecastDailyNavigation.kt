package com.siwakorn.weatherforecast.ui.weatherforecast.daily

import android.os.Parcelable
import com.siwakorn.weatherforecast.domain.weatherforecast.common.WeatherUnit
import kotlinx.parcelize.Parcelize


@Parcelize
data class ForecastDailyNavigation(
    val cityName: String,
    val unit: WeatherUnit
) : Parcelable