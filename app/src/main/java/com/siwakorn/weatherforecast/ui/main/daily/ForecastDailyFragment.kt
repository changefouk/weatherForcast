package com.siwakorn.weatherforecast.ui.main.daily

import android.view.LayoutInflater
import android.view.ViewGroup
import com.siwakorn.weatherforcast.databinding.FragmentForecastDailyBinding
import com.siwakorn.weatherforecast.ui.base.BaseFragment


class ForecastDailyFragment : BaseFragment<FragmentForecastDailyBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentForecastDailyBinding
        get() = FragmentForecastDailyBinding::inflate

    override fun setup() {

    }
}