package com.siwakorn.weatherforecast.ui.base

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.gms.location.*
import com.google.android.gms.location.LocationServices.getFusedLocationProviderClient
import com.siwakorn.weatherforecast.util.CustomDialog
import com.siwakorn.weatherforecast.util.runtimePermission

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    private val fusedLocationClient by lazy { getFusedLocationProviderClient(requireContext()) }

    private val progressLoading: AlertDialog by lazy {
        CustomDialog.getProgressLoading(
            requireContext()
        )
    }

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = _binding as VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    abstract fun setup()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun showProgressLoading() {
        progressLoading.show()
    }

    protected fun hideProgressLoading() {
        progressLoading.cancel()
    }

    protected fun <V : BaseViewModel> V.observeLoading() =
        apply {
            loading.observe(this@BaseFragment, {
                if (it) {
                    showProgressLoading()
                } else {
                    hideProgressLoading()
                }
            })
        }

    protected fun checkPermissionAndGetLocation(
        onSuccess: (Location) -> Unit,
        onFailure: () -> Unit,
        onPermissionNeverAskAgain: () -> Unit
    ) {
        runtimePermission(Manifest.permission.ACCESS_FINE_LOCATION) {
            granted { getLocation(onSuccess, onFailure) }
            rationaleShouldBeShown { _, token -> token.continuePermissionRequest() }
            neverAskAgain { onPermissionNeverAskAgain.invoke() }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation(onSuccess: (Location) -> Unit, onFailure: () -> Unit) {
        val locationRequest = LocationRequest.create()
            .setMaxWaitTime(MAX_Wait_TIME)
        fusedLocationClient.requestLocationUpdates(locationRequest, object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                onSuccess.invoke(locationResult.lastLocation)
                fusedLocationClient.removeLocationUpdates(this)
            }

            override fun onLocationAvailability(locationAvailability: LocationAvailability) {
                if (!locationAvailability.isLocationAvailable) {
                    onFailure.invoke()
                    fusedLocationClient.removeLocationUpdates(this)
                } else {
                    super.onLocationAvailability(locationAvailability)
                }
            }
        }, Looper.getMainLooper())
    }

    companion object {
        private const val MAX_Wait_TIME = 1000L
    }

}