package com.example.themoviedb.login.model

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Credentials

class SharedPreferencesRepositoryImpl : SharedPreferencesRepository {

    companion object {
        const val PREFS_NAME = "PREFS_NAME"
        const val USERNAME_KEY = "USERNAME"
        const val PASSWORD_KEY = "PASSWORD"
    }

    private lateinit var application: Application

    /**
     * Returns
     * true if credentials are right
     * false if credentials are wrong
     */
    override fun getCredentials(credentials: String): Single<Boolean?> {

        val preferences = application.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

        val username = preferences.getString(USERNAME_KEY, "NONE")
        val password = preferences.getString(PASSWORD_KEY, "NONE")

        val registeredCredentials = Credentials.basic(username!!, password!!)

        val isRegistered = registeredCredentials == credentials

        return Single.just(isRegistered)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }

    override fun setCredentials(username: String, password: String): Single<Boolean?> {

        val preferences = application.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

        val editor: SharedPreferences.Editor = preferences.edit()

        val isRegistered = username == preferences.getString( USERNAME_KEY,"NONE")

        if (!isRegistered) {

            editor.apply {

                editor.putString(USERNAME_KEY, username)
                editor.putString(PASSWORD_KEY, password)
                editor.apply()

            }
        }
        return Single.just(!isRegistered)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getApplication(application: Application) {
        this.application = application
    }

}