package com.example.themoviedb.loginvalidation

import android.content.Context
import com.example.themoviedb.application.MyApp
import com.example.themoviedb.application.PREFS_NAME

class SharedPreferencesRepoImpl : SharedPreferencesRepository {

///////////////////////////////////////////////////////////////////////////////////////

    val sharedPreferences = MyApp.getApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override fun returnUsername(usernameKey : String): String {

        return sharedPreferences.getString(usernameKey,"NONE")!!

    }

    override fun returnPassword(passwordKey : String): String

    {

        return sharedPreferences.getString(passwordKey,"NONE")!!

    }

}