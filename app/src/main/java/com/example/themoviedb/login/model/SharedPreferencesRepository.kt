package com.example.themoviedb.login.model

import android.app.Application
import io.reactivex.Single

interface SharedPreferencesRepository {

    fun getCredentials(credentials: String): Single<Boolean?>

    fun setCredentials(username: String, password: String) : Single<Boolean?>

    fun getApplication(application: Application)

}