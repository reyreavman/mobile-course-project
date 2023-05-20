package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RegistrationActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)

        val registrationNicknameText: EditText = findViewById(R.id.registrationNicknameEditText)
        val registrationLoginText: EditText = findViewById(R.id.registrationLoginEditText)
        val registrationPasswordText: EditText = findViewById(R.id.registrationPasswordEditText)
        val registrationButton: Button = findViewById(R.id.registrationButton)
        val checkDataString = "Проверьте введённые данные"
        var registrationNickname: String
        var registrationLogin: String
        var registrationPassword: String
        val database = Firebase.database
        val myRef = database.getReference("message")


        registrationButton.setOnClickListener{
            registrationNickname = registrationNicknameText.text.toString()
            registrationLogin = registrationLoginText.text.toString()
            registrationPassword = registrationPasswordText.text.toString()
            if (!registrationNickname.isEmpty() && !registrationLogin.isEmpty() && !registrationPassword.isEmpty()) {

            }
            else {
                Toast.makeText(applicationContext, checkDataString, Toast.LENGTH_SHORT).show()
            }
        }
    }
}