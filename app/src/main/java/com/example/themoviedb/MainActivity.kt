package com.example.themoviedb


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        btnLogin.setOnClickListener(View.OnClickListener {
        TODO("Implementation for  login")

        })

        registerText.setOnClickListener(View.OnClickListener {
            TODO("Implementation for register")
        })
    }
}
