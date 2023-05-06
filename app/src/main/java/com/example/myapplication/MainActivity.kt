package com.example.myapplication
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button


class MainActivity : AppCompatActivity(), View.OnClickListener {
    val myTag = "myTag"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.signInButton)
        button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val intent = Intent(this, MapActivity::class.java)
        startActivity(intent)
    }

}

