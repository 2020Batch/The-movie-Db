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
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val model = ViewModelProvider(this,
            LoginRegViewModelFactory(
                SharedPreferencesRepositoryImpl(),
                application
            )
        ).get(LoginRegistrationViewModel::class.java)

        btnLogin.setOnClickListener {

            val username = loginUser.text.toString()
            val password = loginPassword.text.toString()

            if (!username.isNullOrBlank()   &&   !password.isNullOrBlank()){

                model.credentialsVerification(loginUser.text.toString(), loginPassword.text.toString())
                model.getVerificationLiveData().observe(this@LoginActivity, Observer<Boolean> {

                        verified ->
                    if (verified) {

                         intent = Intent(this@LoginActivity, HomePageActivity::class.java)

                        startActivity(intent)

                    } else {

                        Toast.makeText(applicationContext, getString(R.string.txt_error_failed_login), Toast.LENGTH_SHORT).show()

                    }

                })
            } else {

                Toast.makeText(
                    applicationContext,
                    "${getString(R.string.error_empty_field)}", Toast.LENGTH_SHORT
                ).show()

            }
        }

        click_to_register.setOnClickListener {

            intent = Intent(this@LoginActivity, RegisterActivity::class.java)

            startActivity(intent)

        }
    }
}
