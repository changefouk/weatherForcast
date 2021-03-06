package com.siwakorn.weatherforecast.ui.weatherforecast.daily

import androidx.lifecycle.viewModelScope
import com.siwakorn.weatherforecast.domain.weatherforecast.forecast.GetForecastBody
import com.siwakorn.weatherforecast.domain.weatherforecast.forecast.GetForecastUseCase
import com.siwakorn.weatherforecast.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ForecastDailyViewModel constructor(
    private val useCase: GetForecastUseCase
) : BaseViewModel() {

    fun getDaily(navigation: ForecastDailyNavigation) {
        viewModelScope.launch {
            useCase.execute(GetForecastBody(navigation.cityName, navigation.unit.unit))
                .flowOn(Dispatchers.IO)
                .onStart { showLoading() }
                .onCompletion { hideLoading() }
                .collect {

                }
        }
    }
}