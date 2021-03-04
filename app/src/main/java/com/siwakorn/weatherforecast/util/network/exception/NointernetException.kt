package com.siwakorn.weatherforecast.util.network.exception

import android.content.Context
import com.siwakorn.weatherforcast.R
import java.io.IOException

class NoInternetException(private val context: Context) : IOException() {
    override val message: String
        get() = context.getString(R.string.no_internet_exception)
}
