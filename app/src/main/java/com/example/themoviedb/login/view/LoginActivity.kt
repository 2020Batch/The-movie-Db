package com.example.themoviedb.login.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.themoviedb.home.HomePageActivity
import com.example.themoviedb.R
import com.example.themoviedb.common.NAME_KEY
import com.example.themoviedb.login.viewmodel.LoginRegViewModelFactory
import com.example.themoviedb.login.viewmodel.LoginRegistrationViewModel
import com.example.themoviedb.login.model.SharedPreferencesRepositoryImpl
import com.example.themoviedb.login.view.RegisterActivity.Companion.PASSWORD_LENGTH
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        title = getString(R.string.txt_title_login)

        val model = ViewModelProvider(this,
            LoginRegViewModelFactory(
                SharedPreferencesRepositoryImpl(),
                application
            )
        ).get(LoginRegistrationViewModel::class.java)

        btnLogin.setOnClickListener {

            val username = loginUser.text.toString()
            val password = loginPassword.text.toString()

            when {

                username.isBlank() -> showToast(1, username)
                password.isBlank() -> showToast(2, username)

                else -> {

                    model.credentialsVerification(
                        loginUser.text.toString(),
                        loginPassword.text.toString()
                    )
                    model.getVerificationLiveData().observe(this@LoginActivity, Observer<Boolean> {

                            verified ->
                        if (verified) {

                            intent = Intent(this@LoginActivity, HomePageActivity::class.java)
                            showToast(5, username)

                            intent.putExtra(NAME_KEY, username)

                            startActivity(intent)
                        } else {
                            showToast(7, username)
                        }
                    })
                }
            }
        }

        click_to_register.setOnClickListener {

            intent = Intent(this@LoginActivity, RegisterActivity::class.java)

            startActivity(intent)

        }
    }

    private fun showToast(type: Int, username : String) {

        when (type) {

            //Empty Username
            1 -> Toast.makeText(
                applicationContext, getString(R.string.txt_error_empty_username),
                Toast.LENGTH_SHORT
            ).show()

            //Empty password
            2 -> Toast.makeText(
                applicationContext, getString(R.string.txt_error_empty_password),
                Toast.LENGTH_SHORT
            ).show()

            //Password too short
            3 -> Toast.makeText(
                applicationContext,
                "${getString(R.string.txt_error_password_too_short)} $PASSWORD_LENGTH ${getString(R.string.txt_error_characters)}",
                Toast.LENGTH_SHORT
            ).show()

            //Registration success
            4 -> Toast.makeText(
                applicationContext,
                "${getString(R.string.txt_successful_registration)} $username",
                Toast.LENGTH_SHORT
            ).show()

            //Login success
            5 -> Toast.makeText(
                applicationContext,
                "${getString(R.string.txt_successful_login)} $username",
                Toast.LENGTH_SHORT
            ).show()

            //Registration failed
            6 -> Toast.makeText(
                applicationContext,
                "$username ${getString(R.string.txt_error_failed_registration)}",
                Toast.LENGTH_SHORT
            ).show()

            //Login failed
            7 -> Toast.makeText(
                applicationContext,
                "${getString(R.string.txt_error_failed_login)} $username",
                Toast.LENGTH_SHORT
            ).show()

        }
    }
}
