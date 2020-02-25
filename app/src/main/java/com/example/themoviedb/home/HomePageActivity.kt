package com.example.themoviedb.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.themoviedb.R
import com.example.themoviedb.movies.list.view.PopularMoviesActivity
import com.example.themoviedb.people.list.view.PopularPeopleActivity
import kotlinx.android.synthetic.main.activity_home_page.*

class HomePageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        btn_movies.setOnClickListener {
            startActivity(
                Intent(this@HomePageActivity, PopularMoviesActivity::class.java)
            )
        }

        btn_people.setOnClickListener {
            startActivity(
                Intent(this@HomePageActivity, PopularPeopleActivity::class.java)
            )
        }
    }
}
