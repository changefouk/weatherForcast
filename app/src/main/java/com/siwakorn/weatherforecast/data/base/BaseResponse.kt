package com.siwakorn.weatherforecast.data.base

import com.google.gson.annotations.SerializedName

open class BaseResponse(
    @SerializedName("cod") val code: Int? = null,
    @SerializedName("message") val message: String? = null
)