package com.siwakorn.weatherforecast.common.network.intercepor

import com.siwakorn.weatherforecast.common.config.ConfigProvider
import com.siwakorn.weatherforecast.util.extension.isSuccess
import com.siwakorn.weatherforecast.common.network.exception.ResponseErrorException
import com.siwakorn.weatherforecast.constant.ApiConstant
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class WeatherForecastInterceptor(
    private val configProvider: ConfigProvider
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val originalHttpUrl = originalRequest.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(ApiConstant.QueryParam.APP_ID, configProvider.apiKey)
            .build()

        val requestBuilder = originalRequest.newBuilder().url(url)
        val request = requestBuilder.build()
        val response = chain.proceed(request)
        if (!response.code.isSuccess()) {
            throw ResponseErrorException(response.message, response.code)
        }
        return response
    }
}