package com.example.themoviedb.Feature07

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviedb.R
import com.example.themoviedb.feature07.*
import kotlinx.android.synthetic.main.activity_popular_movies.*

class PopularMoviesActivity : AppCompatActivity() {

    private lateinit var popMoviesViewModel: PopularMoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popular_movies)



        popMoviesViewModel = ViewModelProvider(this, PopularMoviesViewModelFactory(MDBRepositoryImpl())).get(PopularMoviesViewModel::class.java)

        setProgrsss(true)
        popMoviesViewModel.getPopularMovies()

        popMoviesViewModel.searchLiveDataSuccess.observe(this, Observer {popularMovies ->
            rv_popularMovies.adapter = PopularMoviesAdapter(popularMovies, object: PopularMoviesRecyclerViewClickListener{
                override fun onItemClickedListener(movieId: Int) {
                    val intent = Intent(this@PopularMoviesActivity, MovieDetailScreenActivity::class.java)
                    intent.putExtra(MOVIE_ID_KEY, movieId)
                    startActivity(intent)
                }

            })
            setProgrsss(false)
            rv_popularMovies.visibility = VISIBLE
            rv_popularMovies.layoutManager = LinearLayoutManager(parent)
        })

        popMoviesViewModel.searchLiveDataError.observe(this, Observer {
            error -> tv_errorMessage.text = error
            setError(true)
            rv_popularMovies.visibility = GONE
        })

        btn_retryLoading.setOnClickListener{
            setProgrsss(true)
            popMoviesViewModel.getPopularMovies()
        }
    }

    fun setProgrsss(isShowing: Boolean){
        if(isShowing){
            pb_loadingPopularMovies.visibility = VISIBLE
        }else{
            pb_loadingPopularMovies.visibility = GONE
        }
    }

    fun setError(isShowing: Boolean){
        if(isShowing){
            setProgrsss(false)
            btn_retryLoading.visibility = VISIBLE
            tv_errorMessage.visibility = VISIBLE
        }else{
            btn_retryLoading.visibility = GONE
            tv_errorMessage.visibility = GONE
        }
    }



}
