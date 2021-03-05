package com.siwakorn.weatherforecast.common.network.exception

import java.io.IOException

class ResponseErrorException(
    private val responseErrorMessage: String,
    private val _responseErrorCode: Int
) : IOException() {

    val responseErrorCode: Int
        get() = _responseErrorCode

    override val message: String
        get() = responseErrorMessage
}