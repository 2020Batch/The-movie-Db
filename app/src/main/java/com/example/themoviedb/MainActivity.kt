package com.example.themoviedb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var userLogin: EditText? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userLogin = findViewById<EditText>(R.id.loginUser)
        val loginPassword = findViewById<EditText>(R.id.loginPassword)
        var registerText = findViewById<TextView>(R.id.registerText)
        val btnLogin: Button = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener(View.OnClickListener {
            /*val loginInput = userLogin.text.toString()
            val loginPassword:String = loginPassword.text.toString()
            val goToLogin = Intent(this, LoginActivity::class.java)
            goToLogin.putExtra(object)
            startActivity(goToLogin)*/

        })

        registerText.setOnClickListener(View.OnClickListener {

        })
    }
}
