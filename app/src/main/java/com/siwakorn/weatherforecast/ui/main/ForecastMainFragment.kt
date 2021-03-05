package com.siwakorn.weatherforecast.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.siwakorn.weatherforcast.databinding.FragmentForecastMainBinding
import com.siwakorn.weatherforecast.ui.base.BaseFragment

class ForecastMainFragment : BaseFragment<FragmentForecastMainBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentForecastMainBinding
        get() = FragmentForecastMainBinding::inflate

    override fun setup() {
        binding.test.text = "hello"
    }

}