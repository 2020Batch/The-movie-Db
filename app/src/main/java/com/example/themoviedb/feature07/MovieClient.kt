package com.example.themoviedb.feature07

import com.example.themoviedb.feature07.model.PopularMovieModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieClient {

    @GET(END_POINT_POPULAR_MOVIES)
    fun getPopularMovieRepo(@Query("api_key") key: String?): Observable<PopularMovieModel>

}