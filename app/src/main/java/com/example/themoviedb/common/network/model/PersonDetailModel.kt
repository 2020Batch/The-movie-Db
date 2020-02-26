package com.example.themoviedb.common.network.model


import com.google.gson.annotations.SerializedName

data class PersonDetailModel(
    @SerializedName("biography")
    val biography: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("profile_path")
    val profilePath: String
)