package com.siwakorn.weatherforecast.util

import androidx.fragment.app.Fragment
import com.karumi.dexter.Dexter
import com.karumi.dexter.DexterBuilder
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener

inline fun Fragment.runtimePermission(permission: String, listener: Listener.() -> Unit) {
    val dexter = Dexter.withContext(activity)
    RuntimePermission(dexter)
        .check(permission, Listener().apply(listener))
}

class RuntimePermission(private val dexter: DexterBuilder.Permission) {

    fun check(permission: String, listener: Listener) {
        var isRationaleShouldBeShown = false
        dexter.withPermission(permission)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse) {
                    listener.granted(response)
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest,
                    token: PermissionToken
                ) {
                    listener.rationaleShouldBeShown(permission, token)
                    isRationaleShouldBeShown = true
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) {
                    if (!response.isPermanentlyDenied) {
                        listener.denied(response)
                    } else {
                        listener.neverAskAgain()
                    }
                }
            })
            .check()
    }
}

class Listener(
    var granted: (response: PermissionGrantedResponse) -> Unit = {},
    var denied: (response: PermissionDeniedResponse) -> Unit = {},
    var rationaleShouldBeShown: (permission: PermissionRequest, token: PermissionToken) -> Unit = { _, token -> token.continuePermissionRequest() },
    var neverAskAgain: () -> Unit = {}
) {

    fun granted(onGranted: (response: PermissionGrantedResponse) -> Unit) {
        granted = onGranted
    }

    fun denied(onDenied: (response: PermissionDeniedResponse) -> Unit) {
        denied = onDenied
    }

    fun rationaleShouldBeShown(onRationaleShouldBeShown: (permission: PermissionRequest, token: PermissionToken) -> Unit) {
        rationaleShouldBeShown = onRationaleShouldBeShown
    }

    fun neverAskAgain(block: () -> Unit) {
        neverAskAgain = block
    }
}