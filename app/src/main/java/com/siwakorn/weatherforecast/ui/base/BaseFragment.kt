package com.siwakorn.weatherforecast.ui.base

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.gms.location.*
import com.google.android.gms.location.LocationServices.getFusedLocationProviderClient
import com.siwakorn.weatherforecast.util.runtimePermission

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    private val fusedLocationClient by lazy { getFusedLocationProviderClient(context) }

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

    protected fun checkPermissionLocation(
        onSuccess: (Location) -> Unit,
        onNeverAskAgain: () -> Unit) {
        runtimePermission(Manifest.permission.ACCESS_FINE_LOCATION) {
            granted { getLocation(onSuccess) }
            rationaleShouldBeShown { _, token -> token.continuePermissionRequest() }
            neverAskAgain { onNeverAskAgain.invoke() }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation(onSuccess: (Location) -> Unit) {
        val locationRequest = LocationRequest.create()
        fusedLocationClient.requestLocationUpdates(locationRequest, object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                onSuccess.invoke(locationResult.lastLocation)
            }

            override fun onLocationAvailability(p0: LocationAvailability) {
                super.onLocationAvailability(p0)
            }
        }, Looper.getMainLooper())
    }

}