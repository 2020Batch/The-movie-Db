package com.example.themoviedb.moviedetail.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.themoviedb.R
import com.example.themoviedb.common.BASE_IMAGE_URL
import com.example.themoviedb.moviedetail.model.MovieDetailRepository
import com.example.themoviedb.moviedetail.viewmodel.MovieDetailViewModel
import com.example.themoviedb.moviedetail.viewmodel.MovieDetailViewModelFactory
import com.example.themoviedb.network.model.MovieDetail
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail_screen.*
import kotlinx.android.synthetic.main.error_screen.*

class MovieDetailScreenActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieDetailViewModel

    // TODO("remove it when movies list is merged")
    private val movieId = 18
//  private val movieId = intent.getIntExtra(MOVIE_ID_KEY, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail_screen)

        val viewModelFactory = MovieDetailViewModelFactory(MovieDetailRepository())

        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieDetailViewModel::class.java)

        showMovie()
    }

    private fun showMovie(){
        if (movieId == 0){
            showError(getString(R.string.general_error_message))
        } else {
            setObservableData()

            viewModel.getMovieDetail(movieId)
        }
    }

    private fun showMovieLayout(){
        tv_movie_detail_name.visibility = View.VISIBLE
        tv_movie_detail_description.visibility = View.VISIBLE
        img_movie_detail.visibility = View.VISIBLE
    }

    private fun setObservableData() {
        viewModel.apply {
            movieDetail.observe(this@MovieDetailScreenActivity, Observer { movieDetail ->
                bindData(movieDetail)
                hideProgressBar()
                showMovieLayout()

            })

            errorMessage.observe(this@MovieDetailScreenActivity, Observer { error ->
                showError(error)
            })
        }
    }

    private fun bindData(movieDetail: MovieDetail) {
        tv_movie_detail_name.text = movieDetail.title
        tv_movie_detail_description.text = movieDetail.overview

        val imgUrl = BASE_IMAGE_URL + movieDetail.posterPath

        Picasso
            .get()
            .load(imgUrl)
            .into(img_movie_detail)
    }

    private fun showError(error: String?) {
        if (error == null) {
            error_message.text = getString(R.string.general_error_message)
        } else {
            error_message.text = error
        }

        btn_retry.setOnClickListener {
            showProgressBar()
            showMovie()
        }

        hideData()
        hideProgressBar()
        error_message.visibility = View.VISIBLE
    }

    private fun hideData(){
        tv_movie_detail_name.visibility = View.GONE
        tv_movie_detail_description.visibility = View.GONE
        img_movie_detail.visibility = View.GONE
    }

    private fun hideProgressBar(){
        pb_loading.visibility = View.GONE
    }

    private fun showProgressBar(){
        pb_loading.visibility = View.VISIBLE
    }
}
