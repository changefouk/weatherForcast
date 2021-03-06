package com.siwakorn.weatherforecast.ui.weatherforecast.daily

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.siwakorn.weatherforcast.databinding.FragmentForecastDailyBinding
import com.siwakorn.weatherforecast.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class ForecastDailyFragment : BaseFragment<FragmentForecastDailyBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentForecastDailyBinding
        get() = FragmentForecastDailyBinding::inflate

    private val viewModel: ForecastDailyViewModel by viewModel()
    private val args: ForecastDailyFragmentArgs by navArgs()

    override fun setup() {
        observe()
        viewModel.getDaily(args.data)
    }

    private fun observe() {

    }
}