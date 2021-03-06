package com.siwakorn.weatherforecast.ui.weatherforecast.search

import android.view.LayoutInflater
import android.view.ViewGroup
import com.siwakorn.weatherforcast.databinding.FragmentForecastSearchBinding
import com.siwakorn.weatherforecast.ui.base.BaseFragment
import com.siwakorn.weatherforecast.util.extension.loadImageUrl
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
            binding.ivWeatherIcon.loadImageUrl(it.weather.icon)
        })
    }

}