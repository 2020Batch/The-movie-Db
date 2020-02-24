package com.example.themoviedb.feature07

import com.example.themoviedb.feature07.model.PopularMovieModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MoviesRepositoryImpl() : MoviesRepository {
    private val client = MoviesRetrofitClient.getRetrofitInstance
    private val call = client.getPopularMovieRepo(API_KEY)

    override fun getData(): Observable<PopularMovieModel> {
        return call
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }
}