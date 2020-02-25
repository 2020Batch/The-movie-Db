package com.example.themoviedb


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

                                TODO("If register Success")

                            } else {

                                TODO("If register Success")

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
