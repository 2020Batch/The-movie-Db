package com.example.themoviedb.feature07

import com.example.themoviedb.feature07.model.PopularMovieModel
import io.reactivex.Observable

interface MoviesRepository {
    fun getData(): Observable<PopularMovieModel>
}