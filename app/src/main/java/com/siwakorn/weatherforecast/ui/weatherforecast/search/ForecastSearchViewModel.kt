package com.siwakorn.weatherforecast.ui.weatherforecast.search

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.siwakorn.weatherforcast.R
import com.siwakorn.weatherforecast.common.resource.ResourceProvider
import com.siwakorn.weatherforecast.domain.weatherforecast.common.WeatherUnit
import com.siwakorn.weatherforecast.domain.weatherforecast.weather.GetWeatherBody
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

    private val _weatherUnit = MutableLiveData(WeatherUnit.CELSIUS)
    val weatherUnit: LiveData<WeatherUnit> = _weatherUnit

    private val _weather = MutableLiveData<WeatherResponse>()

    val weatherIconUrl: LiveData<String> =
        _weather.map { resourceProvider.string(R.string.config_weather_icon_url, it.weather.icon) }

    val temp: LiveData<String> = _weather.map {
        if (_weatherUnit.value == WeatherUnit.FAHRENHEIT) {
            resourceProvider.string(R.string.temperature_fahrenheit, it.weatherMain.temp)
        } else {
            resourceProvider.string(R.string.temperature_celsius, it.weatherMain.temp)
        }
    }

    val humidity: LiveData<String> = _weather.map {
        resourceProvider.string(R.string.humidity_percent, it.weatherMain.humidity)
    }

    val cityName: LiveData<String> = _weather.map { it.cityName }
    val dateTime: LiveData<String> = _weather.map { it.getDisplayDateTime() }

    fun getWeather(queryCityName: String? = null, location: Location? = null) {
        viewModelScope.launch {
            val request = GetWeatherBody(
                queryCityName,
                location?.latitude,
                location?.longitude,
                _weatherUnit.value?.unit ?: WeatherUnit.CELSIUS.unit
            )
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

    fun setWeatherUnit(unit: WeatherUnit) {
        _weatherUnit.value = unit
        getWeather(cityName.value)
    }

    fun hasWeatherData(): Boolean = (_weather.value != null)
}