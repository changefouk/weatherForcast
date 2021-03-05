package com.siwakorn.weatherforecast.ui.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import com.siwakorn.weatherforcast.databinding.FragmentForecastSearchBinding
import com.siwakorn.weatherforecast.ui.base.BaseFragment

class ForecastSearchFragment : BaseFragment<FragmentForecastSearchBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentForecastSearchBinding
        get() = FragmentForecastSearchBinding::inflate

    override fun setup() {
        binding.test.text = "hello"
    }

}