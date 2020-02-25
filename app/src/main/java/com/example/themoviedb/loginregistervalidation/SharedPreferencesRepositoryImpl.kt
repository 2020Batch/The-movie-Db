package com.example.themoviedb.loginregistervalidation

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.themoviedb.application.PASSWORD_KEY
import com.example.themoviedb.application.PREFS_NAME
import com.example.themoviedb.application.USERNAME_KEY
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Credentials

class SharedPreferencesRepositoryImpl : SharedPreferencesRepository{

    private lateinit var application: Application

    override fun getCredentials(credentials : String): Single<Boolean?> {

        val preferences = application.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

        val username = preferences.getString(USERNAME_KEY, "NONE")
        val password = preferences.getString(PASSWORD_KEY, "NONE")

        val registeredCredentials = Credentials.basic(username!!, password!!)

        val isRegistered = registeredCredentials == credentials

        return Single.just(isRegistered)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }

    override fun setCredentials(username: String, password: String) : Single<Boolean?> {

        val preferences =  application.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

        val editor: SharedPreferences.Editor = preferences.edit()

        editor.apply {

            editor.putString(USERNAME_KEY, username)
            editor.putString(PASSWORD_KEY, password)
            editor.apply()

        }
        return Single.just(true)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getApplication(application: Application) {
        this.application = application
    }

}