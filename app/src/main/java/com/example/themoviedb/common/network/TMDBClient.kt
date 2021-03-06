package com.example.themoviedb.common.network

import com.example.themoviedb.common.API_KEY_NAME
import com.example.themoviedb.common.MOVIE_DETAIL_ENDPOINT
import com.example.themoviedb.common.POPULAR_MOVIES_ENDPOINT
import com.example.themoviedb.common.POPULAR_PEOPLE_ENDPOINT
import com.example.themoviedb.common.network.model.MovieDetail
import com.example.themoviedb.common.network.model.PopularMovieModel
import com.example.themoviedb.common.network.model.PopularPeopleModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBClient {

    @GET(MOVIE_DETAIL_ENDPOINT)
    fun getMovieDetail(@Path("id") movieId: Int, @Query(API_KEY_NAME) apiKey: String): Single<MovieDetail>

    @GET(POPULAR_PEOPLE_ENDPOINT)
    fun getPopularPeople(@Query(API_KEY_NAME) apiKey: String): Single<PopularPeopleModel>

    @GET(POPULAR_MOVIES_ENDPOINT)
    fun getPopularMovieRepo(@Query(API_KEY_NAME) apiKey: String): Single<PopularMovieModel>
}