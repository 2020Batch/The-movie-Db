package com.example.themoviedb.movies.detail.model

import com.example.themoviedb.common.network.model.MovieDetail
import com.example.themoviedb.common.network.model.SimilarMovieModel
import io.reactivex.Single

interface MovieDetailRepositoryInterface {

    fun downloadMovieDetail(movieId: Int): Single<MovieDetail>

    fun downloadSimilarMovies(movieId: Int): Single<SimilarMovieModel>


}