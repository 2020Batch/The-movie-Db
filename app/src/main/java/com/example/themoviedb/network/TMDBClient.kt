package com.example.themoviedb.network

import com.example.themoviedb.network.model.MovieDetail
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBClient {

    @GET("movie/{id}")
    fun getMovieDetail(@Path("id") movieId: Int, @Query("api_key") apiKey: String): Single<MovieDetail>
}