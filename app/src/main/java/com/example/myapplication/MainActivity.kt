package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var loginString: String
    private lateinit var passwordString: String
    var pref: SharedPreferences? = null
    val constants: Constants = Constants()
    val database = Firebase.database.getReference(constants.USERSTAG)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        val loginEditText: EditText = findViewById(R.id.loginEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)
        val registrationTextView: TextView = findViewById(R.id.toRegistrationTextView)
        val toLoginButton: Button = findViewById(R.id.toLoginButton)
        pref = getSharedPreferences(constants.USERDATA, Context.MODE_PRIVATE)
        val user = User(
            pref?.getString(constants.USERLOGIN, null).toString(),
            pref?.getString(constants.USERPASSWORD, null).toString()
        )
        Log.i(constants.USERDATA, user.login)
        Log.i(constants.USERDATA, user.password)
        if (user.login != constants.NULL || user.password != constants.NULL) {
            val intentToMapActivity = Intent(this@MainActivity, MapActivity::class.java)
            startActivity(intentToMapActivity)
            Toast.makeText(applicationContext, constants.SUCCESSFULIN, Toast.LENGTH_SHORT).show()
        } else {
            registrationTextView.setOnClickListener {
                val intentToRegistrationActivity =
                    Intent(this@MainActivity, RegistrationActivity::class.java)
                startActivity(intentToRegistrationActivity)
            }

            toLoginButton.setOnClickListener {
                loginString = loginEditText.text.toString()
                passwordString = passwordEditText.text.toString()
                if (loginString.isEmpty() || passwordString.isEmpty()) {
                    Toast.makeText(
                        applicationContext,
                        constants.CHECKDATASTRING,
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    user.login = loginString
                    user.password = passwordString
                    user.signIn()
                    if (user.SignInSuccessful) {
                        val intentToMapActivity = Intent(this@MainActivity, MapActivity::class.java)
                        startActivity(intentToMapActivity)
                        saveData(user.login, user.password)
                        Toast.makeText(
                            applicationContext,
                            constants.SUCCESSFULIN,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            constants.CHECKDATASTRING,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    fun saveData(userLogin: String, userPassword: String) {
        val editor = pref?.edit()
        editor?.putString(constants.USERLOGIN, userLogin)
        editor?.putString(constants.USERPASSWORD, userPassword)
        editor?.apply()
    }
}