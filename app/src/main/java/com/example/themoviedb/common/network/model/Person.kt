package com.example.themoviedb.common.network.model

import com.google.gson.annotations.SerializedName

data class Person(
    @SerializedName("name") val name: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("id") val id:Int,
    @SerializedName("profile_path") val profilePath: String
)