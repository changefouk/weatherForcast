package com.siwakorn.weatherforecast.common.resource

import androidx.annotation.StringRes

interface ResourceProvider {

    fun string(@StringRes id: Int): String

    fun string(@StringRes id: Int, value: String): String

    fun string(@StringRes id: Int, vararg strings: String): String

    fun string(@StringRes id: Int, vararg value: Any): String
}