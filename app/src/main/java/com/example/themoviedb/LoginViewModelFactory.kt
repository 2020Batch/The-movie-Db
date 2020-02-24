package com.example.themoviedb

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LoginVMFactory(private val repository : SharedPreferencesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return LoginViewModel as T

    }

}