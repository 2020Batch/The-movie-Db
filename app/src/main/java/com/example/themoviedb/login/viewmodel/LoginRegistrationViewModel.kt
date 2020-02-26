package com.example.themoviedb.login.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.themoviedb.login.model.SharedPreferencesRepository
import io.reactivex.disposables.CompositeDisposable
import okhttp3.Credentials

class LoginRegistrationViewModel(
    private val repository: SharedPreferencesRepository,
    application: Application
) : AndroidViewModel(application) {

    init {
        repository.getApplication(application)
    }

    private val compositeDisposable = CompositeDisposable()

    private val isRegistered = MutableLiveData<Boolean>()

    private val registeredSuccess = MutableLiveData<Boolean>()

    fun getVerificationLiveData(): MutableLiveData<Boolean> {

        return isRegistered

    }

    fun getRegistrationLiveData(): MutableLiveData<Boolean> {

        return registeredSuccess

    }

    fun credentialsVerification(username: String, password: String) {

        val credentials: String = Credentials.basic(username, password)

        compositeDisposable.add(
            repository
                .getCredentials(credentials)
                .subscribe { t -> isRegistered.value = t }

        )
    }

    fun credentialsRegistration(username: String, password: String) {

        compositeDisposable.add(
            repository
                .setCredentials(username, password)
                .subscribe { t -> registeredSuccess.value = t }

        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}