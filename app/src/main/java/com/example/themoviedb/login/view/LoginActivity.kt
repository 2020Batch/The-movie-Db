package com.example.themoviedb.login.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.themoviedb.home.HomePageActivity
import com.example.themoviedb.R
import com.example.themoviedb.login.viewmodel.LoginRegViewModelFactory
import com.example.themoviedb.login.viewmodel.LoginRegistrationViewModel
import com.example.themoviedb.login.model.SharedPreferencesRepositoryImpl
import com.example.themoviedb.login.view.RegisterActivity.Companion.PASSWORD_LENGTH
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val model = ViewModelProvider(
            this,
            LoginRegViewModelFactory(
                SharedPreferencesRepositoryImpl(),
                application
            )
        ).get(LoginRegistrationViewModel::class.java)

        model.getVerificationLiveData()
            .observe(this@LoginActivity, Observer<Boolean> { isRegistered ->
                if (isRegistered) {
                    intent = Intent(this@LoginActivity, HomePageActivity::class.java)
                    startActivity(intent)
                } else {
                    showToast(3)
                }
            })

        btnLogin.setOnClickListener {
            val username = loginUser.text.toString()
            val password = loginPassword.text.toString()

            when {
                username.isBlank() -> showToast(1)
                password.isBlank() -> showToast(2)
                else -> model.credentialsVerification(username, password)
            }
        }

        click_to_register.setOnClickListener {
            intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showToast(type: Int) {

        when (type) {

            //Empty Username
            1 -> Toast.makeText(
                this, getString(R.string.txt_error_empty_username),
                Toast.LENGTH_SHORT
            ).show()

            //Empty password
            2 -> Toast.makeText(
                this, getString(R.string.txt_error_empty_password),
                Toast.LENGTH_SHORT
            ).show()

            //Login failed
            3 -> Toast.makeText(
                    this,
                    getString(R.string.txt_error_failed_login),
                    Toast.LENGTH_SHORT
                ).show()
        }
    }
}
