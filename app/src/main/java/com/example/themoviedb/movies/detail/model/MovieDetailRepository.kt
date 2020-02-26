package com.example.themoviedb.movies.detail.model

import com.example.themoviedb.common.API_KEY
import com.example.themoviedb.common.network.RetrofitFactory
import com.example.themoviedb.common.network.model.MovieDetail
import com.example.themoviedb.common.network.model.SimilarMovieModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieDetailRepository: MovieDetailRepositoryInterface {

    override fun downloadMovieDetail(movieId: Int): Single<MovieDetail> {
        return RetrofitFactory
            .tmdbClient
            .getMovieDetail(movieId, API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun downloadSimilarMovies(movieId: Int): Single<SimilarMovieModel> {
        return RetrofitFactory
            .tmdbClient
            .getSimilarMovies(movieId, API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}