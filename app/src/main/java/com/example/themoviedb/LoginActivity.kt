package com.example.themoviedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.themoviedb.loginregistervalidation.LoginRegViewModelFactory
import com.example.themoviedb.loginregistervalidation.LoginRegistrationViewModel
import com.example.themoviedb.loginregistervalidation.SharedPreferencesRepositoryImpl
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val model = ViewModelProvider(this, LoginRegViewModelFactory(SharedPreferencesRepositoryImpl(), application)
        ).get(LoginRegistrationViewModel::class.java)

        btnLogin.setOnClickListener {

            val username = loginUser.text.toString()
            val password = loginPassword.text.toString()

            if (!username.isNullOrBlank()   &&   !password.isNullOrBlank()){

                model.credentialsVerification(loginUser.text.toString(), loginPassword.text.toString())
                model.getVerificationLiveData().observe(this@LoginActivity, Observer<Boolean> {

                        verified ->
                    if (verified) {

                        TODO("If login Success")

                    } else {

                         TODO("If login failure")

                    }

                })
            } else {

                Toast.makeText(
                    applicationContext,
                    "${getString(R.string.error_empty_field)}", Toast.LENGTH_SHORT
                ).show()

            }
        }
    }
}
