package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Nickname
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class RegistrationActivity : AppCompatActivity() {
    val constants: Constants = Constants()
    lateinit var registrationNickname: String
    lateinit var registrationLogin: String
    lateinit var registrationPassword: String
    var pref: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)
        val registrationLoginText: EditText = findViewById(R.id.registrationLoginEditText)
        val registrationPasswordText: EditText = findViewById(R.id.registrationPasswordEditText)
        val registrationButton: Button = findViewById(R.id.registrationButton)
        val user: User = User()
        pref = getSharedPreferences(constants.USERDATA, Context.MODE_PRIVATE)

        registrationButton.setOnClickListener {
            registrationLogin = registrationLoginText.text.toString()
            registrationPassword = registrationPasswordText.text.toString()
            if (registrationLogin.isEmpty() || registrationPassword.isEmpty()) {
            } else if (registrationLogin.length < 6) {
                Toast.makeText(applicationContext, constants.CHECKLOGINSTRING, Toast.LENGTH_SHORT)
                    .show()
            } else if (registrationPassword.length < 6) {
                Toast.makeText(
                    applicationContext,
                    constants.CHECKPASSWORDSTRING,
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                user.login = registrationLogin
                user.password = registrationPassword
                user.createAccountAtDB()
                if (user.CreateAccountSuccessful) {
                    val intentToMapActivity =
                        Intent(this@RegistrationActivity, MapActivity::class.java)
                    startActivity(intentToMapActivity)
                    saveData(user.login, user.password)
                    Toast.makeText(applicationContext, constants.SUCCESSFULIN, Toast.LENGTH_SHORT)
                        .show()
                } else if (user.UserAlreadyExists) {
                    Toast.makeText(
                        applicationContext,
                        constants.USERALREADYEXISTS,
                        Toast.LENGTH_SHORT
                    ).show()
                } else Toast.makeText(
                    applicationContext,
                    constants.CHECKDATASTRING,
                    Toast.LENGTH_SHORT
                )
                    .show()
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