package com.example.themoviedb.movies.list.model

import com.example.themoviedb.common.network.model.PopularMovieModel
import io.reactivex.Single

interface MdbRepository {
    fun getPopularMovies(): Single<PopularMovieModel>
}