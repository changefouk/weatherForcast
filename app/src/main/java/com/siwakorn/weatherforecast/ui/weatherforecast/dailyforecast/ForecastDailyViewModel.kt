package com.siwakorn.weatherforecast.ui.weatherforecast.dailyforecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.siwakorn.weatherforecast.common.resource.ResourceProvider
import com.siwakorn.weatherforecast.domain.weatherforecast.common.WeatherUnit
import com.siwakorn.weatherforecast.domain.weatherforecast.forecast.ForecastResponse
import com.siwakorn.weatherforecast.domain.weatherforecast.forecast.GetForecastBody
import com.siwakorn.weatherforecast.domain.weatherforecast.forecast.GetForecastUseCase
import com.siwakorn.weatherforecast.ui.base.BaseViewModel
import com.siwakorn.weatherforecast.ui.weatherforecast.dailyforecast.adapter.ForecastDailyAdapterUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ForecastDailyViewModel constructor(
    private val resourceProvider: ResourceProvider,
    private val useCase: GetForecastUseCase
) : BaseViewModel() {

    private var weatherUnit: WeatherUnit = WeatherUnit.CELSIUS

    private val _forecastResponse = MutableLiveData<ForecastResponse>()
    val listDailyForecast: LiveData<List<ForecastDailyAdapterUiModel>> = _forecastResponse.map {
        it.mapListForecastUi(resourceProvider, weatherUnit)
    }
     val cityName: LiveData<String> = _forecastResponse.map { it.city.name }

    fun getDaily(navigation: ForecastDailyNavigation) {
        viewModelScope.launch {
            weatherUnit = navigation.unit
            useCase.execute(GetForecastBody(navigation.cityName, weatherUnit.unit))
                .flowOn(Dispatchers.IO)
                .onStart { showLoading() }
                .onCompletion { hideLoading() }
                .catch { showDialogError(it) }
                .collect {
                    _forecastResponse.value = it
                }
        }
    }
}