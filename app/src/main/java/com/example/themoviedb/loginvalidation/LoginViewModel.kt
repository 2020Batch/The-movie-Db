package com.example.themoviedb.loginvalidation

import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.themoviedb.application.MyApp
import com.example.themoviedb.application.PASSWORD_KEY
import com.example.themoviedb.application.USERNAME_KEY

class LoginViewModel(private val repository: SharedPreferencesRepository) : ViewModel(), LoginViewModelInterface {


    override fun verifyCredentials(username: TextView, password: TextView): Boolean {

        return (username.text == repository.returnUsername(USERNAME_KEY)
                && password.text == repository.returnPassword(PASSWORD_KEY))

    }

    override fun isEmptyUsernameAndPassword(username: TextView, password: TextView) : Boolean  {

        if( username.text.trim() == "" ) {

            Toast.makeText(MyApp.getApplicationContext(),"Username cannot be empty!",Toast.LENGTH_SHORT).show()
           return true
        } else if(password.text.trim() == "" ) {

            Toast.makeText(MyApp.getApplicationContext(),"Password cannot be empty!",Toast.LENGTH_SHORT).show()
            return true

        }
            return false   //If both fields are not empty, false is good to proceed with verifyCredentials()
        }

}