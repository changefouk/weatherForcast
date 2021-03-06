package com.siwakorn.weatherforecast.ui.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.siwakorn.weatherforcast.databinding.FragmentForecastSearchBinding
import com.siwakorn.weatherforecast.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForecastSearchFragment : BaseFragment<FragmentForecastSearchBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentForecastSearchBinding
        get() = FragmentForecastSearchBinding::inflate

    private val viewModel: ForecastSearchViewModel by viewModel()

    override fun setup() {
        observe()
        viewModel.fetch()
    }

    private fun observe() {
        viewModel.weather.observe(viewLifecycleOwner, {
            binding.ivWeatherIcon
        })
    }

}