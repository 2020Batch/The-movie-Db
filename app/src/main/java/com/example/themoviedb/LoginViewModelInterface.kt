package com.example.themoviedb

interface LoginVMInterface {

    fun verifyCredentials(username : String, password : String) : Boolean

}