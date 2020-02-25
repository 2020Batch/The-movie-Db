package com.example.themoviedb.movies.detail.model

import com.example.themoviedb.common.network.model.MovieDetail
import io.reactivex.Single

interface MovieDetailRepositoryInterface {

    fun downloadMovieDetail(movieId: Int): Single<MovieDetail>
}