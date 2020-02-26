package com.example.themoviedb.movies.list.view

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviedb.R
import com.example.themoviedb.common.MOVIE_ID_KEY
import com.example.themoviedb.movies.detail.view.MovieDetailScreenActivity
import com.example.themoviedb.movies.list.model.MdbRepositoryImpl
import com.example.themoviedb.movies.list.viewmodel.PopularMoviesViewModel
import com.example.themoviedb.movies.list.viewmodel.PopularMoviesViewModelFactory
import kotlinx.android.synthetic.main.activity_popular_movies.*

class PopularMoviesActivity : AppCompatActivity() {

    private lateinit var popMoviesViewModel: PopularMoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popular_movies)

        title = getString(R.string.txt_title_popular_movies)

        popMoviesViewModel = ViewModelProvider(this, PopularMoviesViewModelFactory(MdbRepositoryImpl())).get(
            PopularMoviesViewModel::class.java)

        setProgress(true)
        popMoviesViewModel.getPopularMovies()

        popMoviesViewModel.searchLiveDataSuccess.observe(this, Observer {popularMovies ->
            rv_popularMovies.adapter =
                PopularMoviesAdapter(
                    popularMovies,
                    object : PopularMoviesRecyclerViewClickListener {
                        override fun onItemClickedListener(movieId: Int) {
                            val intent = Intent(
                                this@PopularMoviesActivity,
                                MovieDetailScreenActivity::class.java
                            )
                            intent.putExtra(MOVIE_ID_KEY, movieId)
                            startActivity(intent)
                        }
                    })
            setProgress(false)
            rv_popularMovies.visibility = VISIBLE
            rv_popularMovies.layoutManager = LinearLayoutManager(parent)
        })

        popMoviesViewModel.searchLiveDataError.observe(this, Observer {
            error -> tv_errorMessage.text = error
            setError(true)
            rv_popularMovies.visibility = GONE
        })

        btn_retryLoading.setOnClickListener{
            setProgress(true)
            popMoviesViewModel.getPopularMovies()
        }
    }

    fun setProgress(isShowing: Boolean){
        if(isShowing){
            pb_loadingPopularMovies.visibility = VISIBLE
        }else{
            pb_loadingPopularMovies.visibility = GONE
        }
    }

    fun setError(isShowing: Boolean){
        if(isShowing){
            setProgress(false)
            btn_retryLoading.visibility = VISIBLE
            tv_errorMessage.visibility = VISIBLE
        }else{
            btn_retryLoading.visibility = GONE
            tv_errorMessage.visibility = GONE
        }
    }



}
