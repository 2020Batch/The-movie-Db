package com.example.themoviedb.movies.list.model

import com.example.themoviedb.common.API_KEY
import com.example.themoviedb.common.network.RetrofitFactory
import com.example.themoviedb.common.network.model.PopularMovieModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MdbRepositoryImpl(): MdbRepository {

    override fun getPopularMovies(): Single<PopularMovieModel> {
        return RetrofitFactory
            .tmdbClient
            .getPopularMovieRepo(API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}