package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tomtom.quantity.Distance
import com.tomtom.sdk.location.LocationProvider
import com.tomtom.sdk.location.android.AndroidLocationProvider
import com.tomtom.sdk.location.android.AndroidLocationProviderConfig
import com.tomtom.sdk.map.display.MapOptions
import com.tomtom.sdk.map.display.TomTomMap
import com.tomtom.sdk.map.display.camera.CameraOptions
import com.tomtom.sdk.map.display.location.LocationMarkerOptions
import com.tomtom.sdk.map.display.ui.MapFragment
import kotlin.time.Duration.Companion.milliseconds

class MapActivity : AppCompatActivity() {
    val myTag = "TypeOfActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map)
        val checkingSelfPermissions = CheckingSelfPermissions(this)
        val toFindUsersButton: FloatingActionButton = findViewById(R.id.findUsersButton)

        val mapOptions = MapOptions(mapKey = "E5GJL8Q4LVnsok7GyIUKefysGlgqTlTg")
        val mapFragment = MapFragment.newInstance(mapOptions)
        supportFragmentManager.beginTransaction().replace(R.id.map_container, mapFragment).commit()

        val androidLocationProviderConfig = AndroidLocationProviderConfig(
            minTimeInterval = 100L.milliseconds,
            minDistance = Distance.meters(3.0)
        )
        val locationProvider: LocationProvider = AndroidLocationProvider(
            context = applicationContext,
            config = androidLocationProviderConfig
        )
        locationProvider.enable()
        val lastLocation = locationProvider.lastKnownLocation!!.position

        mapFragment.getMapAsync { tomtomMap: TomTomMap ->
            tomtomMap.setLocationProvider(locationProvider)
            tomtomMap.enableLocationMarker(LocationMarkerOptions(type = LocationMarkerOptions.Type.Pointer))
            tomtomMap.moveCamera(
                CameraOptions(
                    position = lastLocation,
                    zoom = 12.0
                )
            )

        }

        toFindUsersButton.setOnClickListener {
            val intentToFindUsersActivity = Intent(this@MapActivity, FindUsersActivity::class.java)
            startActivity(intentToFindUsersActivity)
        }

        Log.i(myTag, "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.i(myTag, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(myTag, "OnResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(myTag, "onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.i(myTag, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(myTag, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(myTag, "onDestroy")
    }
}