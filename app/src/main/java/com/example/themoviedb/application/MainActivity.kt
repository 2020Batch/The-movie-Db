package com.example.themoviedb.application


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.themoviedb.R
import com.example.themoviedb.loginvalidation.LoginViewModel
import com.example.themoviedb.loginvalidation.LoginViewModelFactory
import com.example.themoviedb.loginvalidation.SharedPreferencesRepositoryImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model = ViewModelProvider(
            this,
            LoginViewModelFactory(SharedPreferencesRepositoryImpl(), application)
        ).get(LoginViewModel::class.java)

        model.credentialsRegistration("Arty", "123")

        btnLogin.setOnClickListener {

            val username = loginUser.text.toString()
            val password = loginPassword.text.toString()

            if (!username.isNullOrBlank()   &&   !password.isNullOrBlank()){

                model.credentialsVerification(loginUser.text.toString(), loginPassword.text.toString())
                model.getVerificationLiveData().observe(this@MainActivity, Observer<Boolean> {

                        verified ->
                    if (verified) {
                        println("true")
                    } else {
                        println("false")
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
