package com.siwakorn.weatherforecast.ui.weatherforecast.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.siwakorn.weatherforcast.R
import com.siwakorn.weatherforcast.databinding.FragmentWeatherSearchBinding
import com.siwakorn.weatherforecast.common.network.exception.NoInternetException
import com.siwakorn.weatherforecast.common.network.exception.ResponseDataErrorException
import com.siwakorn.weatherforecast.common.network.exception.ResponseErrorException
import com.siwakorn.weatherforecast.domain.weatherforecast.common.WeatherUnit
import com.siwakorn.weatherforecast.ui.base.BaseFragment
import com.siwakorn.weatherforecast.ui.weatherforecast.dailyforecast.ForecastDailyNavigation
import com.siwakorn.weatherforecast.util.extension.goneView
import com.siwakorn.weatherforecast.util.extension.loadImageUrl
import com.siwakorn.weatherforecast.util.extension.visibleView
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeoutException


class WeatherSearchFragment : BaseFragment<FragmentWeatherSearchBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentWeatherSearchBinding
        get() = FragmentWeatherSearchBinding::inflate

    private val viewModel: WeatherSearchViewModel by viewModel()

    override fun setup() {
        observe()
        setupView()
        initData()
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
                if (binding.edtSearch.text.toString().isNotEmpty()) {
                    viewModel.getWeather(queryCityName = binding.edtSearch.text.toString())
                }
            }
            false
        }
    }

    private fun observe() {
        viewModel.observeLoading()
        viewModel.dialogError.observe(viewLifecycleOwner, {
            handleError(it)
        })
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

        viewModel.showContent.observe(viewLifecycleOwner, {
            if (!binding.clWeatherContent.isShown) {
                showContentView()
            }
        })
    }

    private fun initData() {
        if (!viewModel.hasWeatherData()) {
            viewModel.showLoading()
            checkPermissionAndGetLocation(
                onSuccess = {
                    viewModel.getWeather(location = it)
                },
                onFailure = {
                    viewModel.hideLoading()
                    showErrorView(getString(R.string.error_message_current_location))
                },
                onPermissionNeverAskAgain = {
                    viewModel.hideLoading()
                    showErrorView(getString(R.string.error_message_current_location))
                    Toast.makeText(context, "", Toast.LENGTH_SHORT).show()
                })
        }
    }

    private fun showContentView() {
        binding.errorView.errorViewRoot.goneView()
        binding.clWeatherContent.visibleView()
    }

    private fun showErrorView(message: String) {
        binding.clWeatherContent.goneView()
        binding.errorView.errorViewRoot.visibleView()
        binding.errorView.tvErrorMessage.text = message
    }

    private fun handleError(it: Throwable?) {
        when (it) {
            is ResponseErrorException -> showErrorView(message = it.message)
            is NoInternetException -> showErrorView(message = it.message)
            is ResponseDataErrorException -> showErrorView(message = it.message)
            else -> showErrorView(getString(R.string.error_message_current_location))
        }
    }

}