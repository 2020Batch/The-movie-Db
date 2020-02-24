package com.example.themoviedb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.themoviedb.moviedetail.view.MovieDetailScreenActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(
            Intent(this, MovieDetailScreenActivity::class.java)
        )
    }
}
