package com.siwakorn.weatherforecast.util

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.siwakorn.weatherforcast.databinding.LayoutProgressLoadingBinding

object CustomDialog {
    fun getProgressLoading(context: Context): AlertDialog {
        val binding = LayoutProgressLoadingBinding.inflate(LayoutInflater.from(context))
        return AlertDialog.Builder(context).setView(binding.root).setCancelable(false).create()
    }
}