package com.siwakorn.weatherforecast.di

import android.content.Context
import com.siwakorn.weatherforecast.common.config.ConfigProvider
import com.siwakorn.weatherforecast.common.network.intercepor.InternetConnectionInterceptor
import com.siwakorn.weatherforecast.common.network.intercepor.ResponseConnectionInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    // interceptor
    single { provideHttpLoggingInterceptor() }
    single { provideInternetConnectionInterceptor(androidContext()) }
    single { provideResponseConnectionInterceptor() }

    // okhttp & retrofit
    single { provideOkHttpClient(get(), get(), get()) }
    single { provideRetrofit(get(), get()) }
}

private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

private fun provideInternetConnectionInterceptor(context: Context): InternetConnectionInterceptor =
    InternetConnectionInterceptor(context)

private fun provideResponseConnectionInterceptor(): ResponseConnectionInterceptor =
    ResponseConnectionInterceptor()

private fun provideOkHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor,
    connectionInterceptor: InternetConnectionInterceptor,
    responseConnectionInterceptor: ResponseConnectionInterceptor
): OkHttpClient = OkHttpClient()
    .newBuilder()
    .connectTimeout(30L, TimeUnit.SECONDS)
    .readTimeout(30L, TimeUnit.SECONDS)
    .addNetworkInterceptor(httpLoggingInterceptor)
    .addInterceptor(connectionInterceptor)
    .addInterceptor(responseConnectionInterceptor)
    .build()

private fun provideRetrofit(okHttpClient: OkHttpClient, configProvider: ConfigProvider) =
    Retrofit.Builder().baseUrl(configProvider.baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()