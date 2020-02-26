package com.example.themoviedb.common.network

import com.example.themoviedb.common.*
import com.example.themoviedb.common.network.model.MovieDetail
import com.example.themoviedb.common.network.model.PopularMovieModel
import com.example.themoviedb.common.network.model.PopularPeopleModel
import com.example.themoviedb.common.network.model.SimilarMovieModel
import io.reactivex.Observable
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

    @GET(MOVIE_DETAIL_SIMILAR_ENDPOINT)
    fun getSimilarMovies(
        @Path("id") movieId: Int,
        @Query(API_KEY_NAME) apiKey: String
    ): Single<SimilarMovieModel>
}