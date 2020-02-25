package com.example.themoviedb


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.themoviedb.application.PASSWORD_LENGTH
import com.example.themoviedb.loginregistervalidation.LoginRegViewModelFactory
import com.example.themoviedb.loginregistervalidation.LoginRegistrationViewModel
import com.example.themoviedb.loginregistervalidation.SharedPreferencesRepositoryImpl
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val model = ViewModelProvider(this, LoginRegViewModelFactory(SharedPreferencesRepositoryImpl(), application)
        ).get(LoginRegistrationViewModel::class.java)

        btn_register.setOnClickListener {

            val username = et_reg_user_name.text.toString()
            val password = et_reg_password.text.toString()

            if (password.length < PASSWORD_LENGTH) {

                Toast.makeText(applicationContext, "Password should be at least $PASSWORD_LENGTH characters long",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                if (!username.isNullOrBlank() && !password.isNullOrBlank()) {

                    model.credentialsRegistration(username, password)
                    model.getRegistrationLiveData()
                        .observe(this@RegisterActivity, Observer<Boolean> {

                                registered ->

                            if (registered) {

                                intent = Intent(this@RegisterActivity, LoginActivity::class.java)

                                Toast.makeText(
                                    applicationContext,
                                    "${getString(R.string.txt_successful_registration)}", Toast.LENGTH_SHORT
                                ).show()

                                startActivity(intent)

                            } else {

                                Toast.makeText(
                                    applicationContext,
                                    "${getString(R.string.txt_error_failed_registration)}", Toast.LENGTH_SHORT
                                ).show()

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
}
