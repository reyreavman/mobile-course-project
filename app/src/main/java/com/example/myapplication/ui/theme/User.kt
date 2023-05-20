package com.example.myapplication.ui.theme

import kotlin.properties.Delegates

class User() {
    private var ID by Delegates.notNull<Int>()
    private lateinit var NICKNAME: String
    private lateinit var LOGIN: String
    private lateinit var PASSWORD: String

    constructor(_ID: Int, _NICKNAME: String, _LOGIN: String, _PASSWORD: String): this() {
        ID = _ID
        NICKNAME = _NICKNAME
        LOGIN = _LOGIN
        PASSWORD = _PASSWORD
    }
}