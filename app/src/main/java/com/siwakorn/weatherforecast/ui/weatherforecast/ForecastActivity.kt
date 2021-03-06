package com.siwakorn.weatherforecast.ui.weatherforecast

import android.view.LayoutInflater
import com.siwakorn.weatherforcast.databinding.ActivityForecastBinding
import com.siwakorn.weatherforecast.ui.base.BaseActivity


class ForecastActivity : BaseActivity<ActivityForecastBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityForecastBinding
        get() = ActivityForecastBinding::inflate

    override fun setup() {

    }

}
