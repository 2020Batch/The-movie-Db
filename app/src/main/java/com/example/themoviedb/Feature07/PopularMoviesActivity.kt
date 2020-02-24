package com.example.themoviedb.Feature07

import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviedb.R
import com.example.themoviedb.feature07.MDBRepositoryImpl
import com.example.themoviedb.feature07.PopularMoviesViewModel
import com.example.themoviedb.feature07.PopularMoviesViewModelFactory
import kotlinx.android.synthetic.main.activity_popular_movies.*

class PopularMoviesActivity : AppCompatActivity() {

    private lateinit var popMoviesViewModel: PopularMoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popular_movies)



        popMoviesViewModel = ViewModelProvider(this, PopularMoviesViewModelFactory(MDBRepositoryImpl())).get(PopularMoviesViewModel::class.java)

        popMoviesViewModel.getPopularMovies()


        popMoviesViewModel.searchLiveDataSuccess.observe(this, Observer {popularMovies ->
            rv_popularMovies.adapter = PopularMoviesAdapter(popularMovies)
            rv_popularMovies.layoutManager = LinearLayoutManager(parent)
        })

        popMoviesViewModel.searchLiveDataError.observe(this, Observer {
            error -> Log.i("LogTag", error)
        })


    }


    fun setProgrsss(isShowing: Boolean){
        if(isShowing){
            pb_loadingPopularMovies.visibility = VISIBLE
        }else{
            pb_loadingPopularMovies.visibility = GONE
        }
    }

}
