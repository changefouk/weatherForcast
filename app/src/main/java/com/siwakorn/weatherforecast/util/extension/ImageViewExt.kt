package com.siwakorn.weatherforecast.util.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.siwakorn.weatherforcast.R

fun ImageView.loadImageUrl(url: String?) {
    Glide.with(this.context)
        .load(url)
        .error(R.drawable.ic_error)
        .into(this)
}