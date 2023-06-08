package com.example.myapplication


import android.os.Environment
import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class User() {
    val constants: Constants = Constants()
    var login: String = ""
    var password: String = ""
    var CreateAccountSuccessful = false
    var SignInSuccessful = false
    var UserAlreadyExists = false
    val database: DatabaseReference = Firebase.database.reference

    constructor(_LOGIN: String, _PASSWORD: String) : this() {
        login = _LOGIN
        password = _PASSWORD
    }

    fun signIn() {
        database.child(constants.USERSTAG).child(login).get().addOnSuccessListener {
            if (it.value.toString() == constants.NULL) Log.i(
                constants.FIREBASETAG,
                constants.LOGINNOTFOUND
            )
            else if (it.value.toString()
                    .substring(10, it.value.toString().length - 1) == password
            ) SignInSuccessful = true
        }.addOnFailureListener {
            Log.i(constants.FIREBASETAG, constants.WEDONTGETIT)
            SignInSuccessful = false
        }
    }

    fun createAccountAtDB() {
        database.child(constants.USERSTAG).child(login).get().addOnSuccessListener {
            if (it.value.toString() == constants.NULL) {
                database.child(constants.USERSTAG).child(login)
                database.child(constants.USERSTAG).child(login).child(constants.PASSWORD)
                    .setValue(password)
                CreateAccountSuccessful = true
            } else UserAlreadyExists = true
        }
    }
}