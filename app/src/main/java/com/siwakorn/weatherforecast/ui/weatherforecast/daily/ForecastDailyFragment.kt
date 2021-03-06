package com.siwakorn.weatherforecast.ui.weatherforecast.daily

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.siwakorn.weatherforcast.databinding.FragmentForecastDailyBinding
import com.siwakorn.weatherforecast.ui.base.BaseFragment
import com.siwakorn.weatherforecast.ui.weatherforecast.daily.adapter.ForecastDailyAdapter
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
        viewModel.listDailyForecast.observe(viewLifecycleOwner, {
            forecastDailyAdapter.setData(it)
        })

        viewModel.cityName.observe(viewLifecycleOwner, {
            binding.tvForecastDailyCity.text = it
        })
    }
}