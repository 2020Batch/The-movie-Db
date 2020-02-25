package com.example.themoviedb.login.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.themoviedb.login.model.SharedPreferencesRepository

class LoginRegViewModelFactory(private val repository: SharedPreferencesRepository, private val application: Application) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return LoginRegistrationViewModel(
            repository,
            application
        ) as T

    }
}