package com.example.themoviedb.common.network.model

import com.google.gson.annotations.SerializedName

data class SimilarMovieModel (
    @SerializedName("results") val listOfSimilarMovies: List<MovieDetail>
    )