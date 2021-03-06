package com.siwakorn.weatherforecast.ui.weatherforecast.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.siwakorn.weatherforcast.R
import com.siwakorn.weatherforecast.common.resource.ResourceProvider
import com.siwakorn.weatherforecast.domain.weatherforecast.common.WeatherUnit
import com.siwakorn.weatherforecast.domain.weatherforecast.weather.GetWeatherForecastBody
import com.siwakorn.weatherforecast.domain.weatherforecast.weather.GetWeatherUseCase
import com.siwakorn.weatherforecast.domain.weatherforecast.weather.WeatherResponse
import com.siwakorn.weatherforecast.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ForecastSearchViewModel constructor(
    private val resourceProvider: ResourceProvider,
    private val useCase: GetWeatherUseCase
) : BaseViewModel() {

    private val _weather = MutableLiveData<WeatherResponse>()

    val weatherIconUrl: LiveData<String> =
        _weather.map { resourceProvider.string(R.string.config_weather_icon_url, it.weather.icon) }

    val temp: LiveData<String> = _weather.map { it.weather.main }

    fun fetch() {
        viewModelScope.launch {
            val request = GetWeatherForecastBody(13.756331, 100.501762, WeatherUnit.CELSIUS.unit)
            useCase.execute(request)
                .flowOn(Dispatchers.IO)
                .onStart { showLoading() }
                .onCompletion { hideLoading() }
                .catch { showDialogError(it) }
                .collect {
                    _weather.value = it
                }
        }
    }
}