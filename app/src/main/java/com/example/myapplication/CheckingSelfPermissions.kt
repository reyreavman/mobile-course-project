package com.example.myapplication

import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

class CheckingSelfPermissions(_ACTIVITY: MapActivity) {
    val activity = _ACTIVITY

    init {
        checkSelfPermissions(activity)
    }

    private fun checkSelfPermissions(_activity: MapActivity) {
        if (ActivityCompat.checkSelfPermission(
                activity,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                activity,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            val permissions = arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
            ActivityCompat.requestPermissions(activity, permissions, 0)
        }
    }

}