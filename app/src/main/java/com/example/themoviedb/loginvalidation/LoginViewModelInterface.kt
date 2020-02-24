package com.example.themoviedb.loginvalidation

import android.widget.TextView

interface LoginViewModelInterface {

    fun verifyCredentials(username: TextView, password: TextView) : Boolean

    fun isEmptyUsernameAndPassword(username: TextView, password: TextView) : Boolean

}