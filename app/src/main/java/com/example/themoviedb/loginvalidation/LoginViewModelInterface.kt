package com.example.themoviedb.loginvalidation

interface LoginViewModelInterface {

    fun verifyCredentials(usernameVerification: Boolean, passwordVerification:Boolean)

    fun usernameVerification(username : String) :Boolean

    fun passWordVerification(password : String): Boolean

    fun isEmpty(field: String): Boolean

}