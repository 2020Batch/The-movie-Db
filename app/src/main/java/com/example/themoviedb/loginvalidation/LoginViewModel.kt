package com.example.themoviedb.loginvalidation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel(private val repository: SharedPreferencesRepository) : ViewModel(), LoginViewModelInterface {

    val verifyCredentials = MutableLiveData<Boolean>()

    val compositeDisposable = CompositeDisposable()

    override fun verifyCredentials(usernameVerification: Boolean, passwordVerification: Boolean) {

        verifyCredentials.value = usernameVerification && passwordVerification

    }

    override fun usernameVerification(username: String): Boolean {

        return compositeDisposable.add(
            repository
                .returnUsername(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{t -> if(t == username){return@subscribe}}
        )
    }

    override fun passWordVerification(password: String): Boolean {

       return compositeDisposable.add(
            repository
                .returnUsername(password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{t -> if(t == password){return@subscribe}}
        )
    }


    override fun isEmpty(field: String) : Boolean {

        return field.trim() == ""  //fields are not empty, false is good to proceed with verifyCredentials()

    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}