package com.siwakorn.weatherforecast.ui.weatherforecast.dailyforecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.siwakorn.weatherforcast.R
import com.siwakorn.weatherforcast.databinding.FragmentForecastDailyBinding
import com.siwakorn.weatherforecast.common.network.exception.NoInternetException
import com.siwakorn.weatherforecast.common.network.exception.ResponseDataErrorException
import com.siwakorn.weatherforecast.common.network.exception.ResponseErrorException
import com.siwakorn.weatherforecast.ui.base.BaseFragment
import com.siwakorn.weatherforecast.ui.weatherforecast.dailyforecast.adapter.ForecastDailyAdapter
import com.siwakorn.weatherforecast.util.extension.goneView
import com.siwakorn.weatherforecast.util.extension.visibleView
import org.koin.androidx.viewmodel.ext.android.viewModel


class ForecastDailyFragment : BaseFragment<FragmentForecastDailyBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentForecastDailyBinding
        get() = FragmentForecastDailyBinding::inflate

    private val viewModel: ForecastDailyViewModel by viewModel()
    private val args: ForecastDailyFragmentArgs by navArgs()

    private val forecastDailyAdapter by lazy { ForecastDailyAdapter() }

    override fun setup() {
        observe()
        initView()
        viewModel.getDaily(args.data)
    }

    private fun initView() {
        binding.rvForecast.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = forecastDailyAdapter
        }

        binding.ivBackButton.setOnClickListener { activity?.onBackPressed() }
    }

    private fun observe() {
        viewModel.observeLoading()
        viewModel.listDailyForecast.observe(viewLifecycleOwner, {
            forecastDailyAdapter.setData(it)
            showContentView()
        })

        viewModel.cityName.observe(viewLifecycleOwner, {
            binding.tvForecastDailyCity.text = it
        })

        viewModel.dialogError.observe(viewLifecycleOwner, {
            handleError(it)
        })
    }

    private fun showContentView() {
        binding.errorView.errorViewRoot.goneView()
        binding.clForecastContent.visibleView()
    }

    private fun showErrorView(message: String) {
        binding.clForecastContent.goneView()
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