package com.example.themoviedb.common.network.model


import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @SerializedName("id") val id: Int,
    @SerializedName("imdb_id") val imdbId: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("title") val title: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int
)