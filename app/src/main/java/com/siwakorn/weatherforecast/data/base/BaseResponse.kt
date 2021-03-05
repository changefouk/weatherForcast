package com.siwakorn.weatherforecast.data.base

import com.google.gson.annotations.SerializedName

open class BaseResponse(
    @SerializedName("cod") val code: String? = null,
    @SerializedName("message") val message: String? = null
)