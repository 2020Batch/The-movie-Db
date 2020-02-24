package com.example.themoviedb.loginvalidation

import android.content.SharedPreferences
import android.widget.TextView
import io.reactivex.Single

interface SharedPreferencesRepository {

    fun returnUsername(usernameKey : String): Single<String>

    fun returnPassword(passwordKey : String): Single<String>

}