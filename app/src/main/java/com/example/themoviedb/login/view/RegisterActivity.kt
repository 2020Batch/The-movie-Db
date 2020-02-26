package com.example.themoviedb.login.view


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.themoviedb.R
import com.example.themoviedb.login.viewmodel.LoginRegViewModelFactory
import com.example.themoviedb.login.viewmodel.LoginRegistrationViewModel
import com.example.themoviedb.login.model.SharedPreferencesRepositoryImpl
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    companion object {
        const val PASSWORD_LENGTH = 6
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val model = ViewModelProvider(
            this,
            LoginRegViewModelFactory(
                SharedPreferencesRepositoryImpl(),
                application
            )
        ).get(LoginRegistrationViewModel::class.java)

        model.getRegistrationLiveData()
            .observe(this@RegisterActivity, Observer<Boolean> { registered ->

                if (registered) {

                    intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    showToast(4)
                    startActivity(intent)

                } else {
                    showToast(6)
                }
            })

        btn_register.setOnClickListener {

            val username = et_reg_user_name.text.toString()
            val password = et_reg_password.text.toString()

            when {
                username.isBlank() -> {
                    showToast(1)
                }
                password.isBlank() -> {
                    showToast(2)
                }
                password.length < PASSWORD_LENGTH -> {
                    showToast(3)
                }
                else -> {

                    model.credentialsRegistration(username, password)
                }
            }

        }
    }

    private fun showToast(type: Int) {

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
                "${getString(R.string.txt_successful_registration)} ${et_reg_user_name.text}",
                Toast.LENGTH_SHORT
            ).show()

            //Registration failed
            6 -> Toast.makeText(
                applicationContext,
                "${et_reg_user_name.text} ${getString(R.string.txt_error_failed_registration)}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}
