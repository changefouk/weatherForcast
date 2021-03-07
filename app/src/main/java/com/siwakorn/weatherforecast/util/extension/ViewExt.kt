package com.siwakorn.weatherforecast.util.extension

import android.view.View

fun View.goneView() {
    this.visibility = View.GONE
}

fun View.visibleView() {
    this.visibility = View.VISIBLE
}