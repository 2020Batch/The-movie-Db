package com.example.themoviedb.moviedetail.model

import com.example.themoviedb.network.model.MovieDetail
import io.reactivex.Single

interface MovieDetailRepositoryInterface {

    fun downloadMovieDetail(movieId: Int): Single<MovieDetail>
}