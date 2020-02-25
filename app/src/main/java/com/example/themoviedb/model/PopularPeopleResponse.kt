package com.example.themoviedb.model


import com.google.gson.annotations.SerializedName

data class PopularPeopleResponse(

    @SerializedName("results")
    val results: List<Result>
) {
    data class Result(
        @SerializedName("name")
        val name: String, // Bill Pullman
        @SerializedName("popularity")
        val popularity: Double, // 22.35
        @SerializedName("profile_path")
        val profilePath: String // /pIpTEQVbDif8m8OdjAxQKNCj0D6.jpg
    )
}