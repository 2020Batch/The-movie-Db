package com.example.themoviedb.moviedetail.model

import com.example.themoviedb.common.API_KEY
import com.example.themoviedb.network.RetrofitFactory
import com.example.themoviedb.network.model.MovieDetail
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
}