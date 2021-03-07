package com.siwakorn.weatherforecast.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent
import java.lang.Exception

abstract class BaseViewModel : ViewModel() {

    protected val _loading by lazy { LiveEvent<Boolean>() }
    val loading: LiveData<Boolean> by lazy { _loading }

    protected val _dialogError by lazy { LiveEvent<Throwable>() }
    val dialogError: LiveData<Throwable> by lazy { _dialogError }

    open fun showLoading() {
        _loading.value = true
    }

    open fun hideLoading() {
        _loading.value = false
    }

    open fun showDialogError(e: Throwable) {
        _dialogError.value = e
    }

}