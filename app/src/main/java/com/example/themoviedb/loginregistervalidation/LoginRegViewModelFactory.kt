package com.example.themoviedb.loginregistervalidation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LoginRegViewModelFactory(private val repository: SharedPreferencesRepository, private val application: Application) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return LoginRegistrationViewModel(repository,application) as T

    }
}