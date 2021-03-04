package com.siwakorn.weatherforecast.util.network.intercepor

import com.siwakorn.weatherforecast.util.extension.isSuccess
import com.siwakorn.weatherforecast.util.network.exception.ResponseErrorException
import okhttp3.Interceptor
import okhttp3.Response

class ResponseConnectionInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (!response.code.isSuccess()) {
            throw ResponseErrorException(response.message, response.code)
        }
        return response
    }
}