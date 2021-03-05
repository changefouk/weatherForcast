package com.siwakorn.weatherforecast.ui.main.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siwakorn.weatherforecast.domain.weatherforecast.common.WeatherUnit
import com.siwakorn.weatherforecast.domain.weatherforecast.weather.GetWeatherForecastBody
import com.siwakorn.weatherforecast.domain.weatherforecast.weather.GetWeatherUseCase
import com.siwakorn.weatherforecast.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ForecastSearchViewModel constructor(
    private val useCase: GetWeatherUseCase
) : BaseViewModel() {

    fun fetch() {
        viewModelScope.launch {
            val request = GetWeatherForecastBody(13.756331, 100.501762, WeatherUnit.CELSIUS.unit)
            useCase.execute(request)
                .flowOn(Dispatchers.IO)
                .onStart { showLoading() }
                .onCompletion { hideLoading() }
                .catch { showDialogError(it) }
                .collect {  }
        }
    }
}