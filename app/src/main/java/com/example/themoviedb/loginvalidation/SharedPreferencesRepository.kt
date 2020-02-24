package com.example.themoviedb.loginvalidation

import android.content.SharedPreferences
import android.widget.TextView

interface SharedPreferencesRepository {

    fun returnUsername(usernameKey : String): String

    fun returnPassword(passwordKey : String): String

}