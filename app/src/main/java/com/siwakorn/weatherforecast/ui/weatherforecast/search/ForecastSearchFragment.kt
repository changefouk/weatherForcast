package com.siwakorn.weatherforecast.ui.weatherforecast.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.siwakorn.weatherforcast.databinding.FragmentForecastSearchBinding
import com.siwakorn.weatherforcast.databinding.LayoutWeatherDetailBinding
import com.siwakorn.weatherforecast.ui.base.BaseFragment
import com.siwakorn.weatherforecast.util.extension.loadImageUrl
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForecastSearchFragment : BaseFragment<FragmentForecastSearchBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentForecastSearchBinding
        get() = FragmentForecastSearchBinding::inflate

    private val weatherDetailBinding: LayoutWeatherDetailBinding by lazy { binding.weatherDetail }

    private val viewModel: ForecastSearchViewModel by viewModel()

    override fun setup() {
        observe()
        viewModel.fetch()
    }

    private fun observe() {
        viewModel.weatherIconUrl.observe(viewLifecycleOwner, {
            weatherDetailBinding.ivWeather.loadImageUrl(it)
        })

        viewModel.temp.observe(viewLifecycleOwner, {
            weatherDetailBinding.tvWeatherTemp.text = it
        })

        viewModel.humidity.observe(viewLifecycleOwner, {
            weatherDetailBinding.tvWeatherHumidity.text = it
        })

        viewModel.cityName.observe(viewLifecycleOwner, {
            weatherDetailBinding.tvWeatherCity.text = it
        })

        viewModel.dateTime.observe(viewLifecycleOwner, {
            weatherDetailBinding.tvWeatherDate.text = it
        })
    }

}