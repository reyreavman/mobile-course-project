package com.example.myapplication
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log



class MainActivity : AppCompatActivity() {
    val myTag = "myTag"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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

