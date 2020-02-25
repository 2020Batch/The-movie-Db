package com.example.themoviedb.loginvalidation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LoginViewModelFactory(private val repository: SharedPreferencesRepository,private val application: Application) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return LoginRegistrationViewModel(repository,application) as T

    }
}