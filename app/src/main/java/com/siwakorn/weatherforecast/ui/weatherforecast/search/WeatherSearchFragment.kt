package com.siwakorn.weatherforecast.ui.weatherforecast.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.siwakorn.weatherforcast.databinding.FragmentWeatherSearchBinding
import com.siwakorn.weatherforcast.databinding.LayoutWeatherDetailBinding
import com.siwakorn.weatherforecast.domain.weatherforecast.common.WeatherUnit
import com.siwakorn.weatherforecast.ui.base.BaseFragment
import com.siwakorn.weatherforecast.ui.weatherforecast.daily.ForecastDailyNavigation
import com.siwakorn.weatherforecast.util.extension.loadImageUrl
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherSearchFragment : BaseFragment<FragmentWeatherSearchBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentWeatherSearchBinding
        get() = FragmentWeatherSearchBinding::inflate

    private val viewModel: ForecastSearchViewModel by viewModel()

    override fun setup() {
        viewModel.fetch()
        binding.btnSeeForecast.setOnClickListener {
            findNavController().navigate(
                WeatherSearchFragmentDirections.actionForecastMainFragmentToForecastDailyFragment(
                    ForecastDailyNavigation(cityName = "london", unit = WeatherUnit.CELSIUS)
                )
            )
        }
        observe()
    }

    private fun observe() {
        viewModel.weatherIconUrl.observe(viewLifecycleOwner, {
            binding.weatherDetail.ivWeather.loadImageUrl(it)
        })

        viewModel.temp.observe(viewLifecycleOwner, {
            binding.weatherDetail.tvWeatherTemp.text = it
        })

        viewModel.humidity.observe(viewLifecycleOwner, {
            binding.weatherDetail.tvWeatherHumidity.text = it
        })

        viewModel.cityName.observe(viewLifecycleOwner, {
            binding.weatherDetail.tvWeatherCity.text = it
        })

        viewModel.dateTime.observe(viewLifecycleOwner, {
            binding.weatherDetail.tvWeatherDate.text = it
        })
    }

}