package com.siwakorn.weatherforecast.ui.weatherforecast.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.navigation.fragment.findNavController
import com.siwakorn.weatherforcast.databinding.FragmentWeatherSearchBinding
import com.siwakorn.weatherforecast.domain.weatherforecast.common.WeatherUnit
import com.siwakorn.weatherforecast.ui.base.BaseFragment
import com.siwakorn.weatherforecast.ui.weatherforecast.dailyforecast.ForecastDailyNavigation
import com.siwakorn.weatherforecast.util.extension.loadImageUrl
import org.koin.androidx.viewmodel.ext.android.viewModel


class WeatherSearchFragment : BaseFragment<FragmentWeatherSearchBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentWeatherSearchBinding
        get() = FragmentWeatherSearchBinding::inflate

    private val viewModel: WeatherSearchViewModel by viewModel()

    override fun setup() {
        observe()
        setupView()
        initData()
    }

    private fun initData() {
        if (!viewModel.hasWeatherData()) {
            checkPermissionLocation(
                onSuccess = {
                    viewModel.getWeather(location = it)
                },
                onNeverAskAgain = {
                    // TODO when nerver ask issue
                })
        }
    }

    private fun setupView() {
        binding.btnSeeForecast.setOnClickListener {
            findNavController().navigate(
                WeatherSearchFragmentDirections.actionForecastMainFragmentToForecastDailyFragment(
                    ForecastDailyNavigation(
                        cityName = viewModel.cityName.value ?: "",
                        unit = viewModel.weatherUnit.value ?: WeatherUnit.CELSIUS
                    )
                )
            )
        }

        binding.tbTemp.setOnCheckedChangeListener { _, isFahrenheit ->
            if (isFahrenheit) {
                viewModel.setWeatherUnit(WeatherUnit.FAHRENHEIT)
            } else {
                viewModel.setWeatherUnit(WeatherUnit.CELSIUS)
            }
        }

        binding.edtSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.getWeather(queryCityName = binding.edtSearch.text.toString())
            }
            false
        }
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