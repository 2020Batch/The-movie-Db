package com.example.themoviedb.model


import com.google.gson.annotations.SerializedName

data class PopularPeopleResponse(
    @SerializedName("page")
    val page: Int, // 1
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int, // 500
    @SerializedName("total_results")
    val totalResults: Int // 10000
) {
    data class Result(
        @SerializedName("adult")
        val adult: Boolean, // false
        @SerializedName("gender")
        val gender: Int, // 2
        @SerializedName("id")
        val id: Int, // 8984
        @SerializedName("known_for")
        val knownFor: List<KnownFor>,
        @SerializedName("known_for_department")
        val knownForDepartment: String, // Acting
        @SerializedName("name")
        val name: String, // Bill Pullman
        @SerializedName("popularity")
        val popularity: Double, // 22.35
        @SerializedName("profile_path")
        val profilePath: String // /pIpTEQVbDif8m8OdjAxQKNCj0D6.jpg
    ) {
        data class KnownFor(
            @SerializedName("adult")
            val adult: Boolean, // false
            @SerializedName("backdrop_path")
            val backdropPath: String, // /mou66QcacavDahfyuaqKfaCLdoI.jpg
            @SerializedName("genre_ids")
            val genreIds: List<Int>,
            @SerializedName("id")
            val id: Int, // 47933
            @SerializedName("media_type")
            val mediaType: String, // movie
            @SerializedName("original_language")
            val originalLanguage: String, // en
            @SerializedName("original_title")
            val originalTitle: String, // Independence Day: Resurgence
            @SerializedName("overview")
            val overview: String, // We always knew they were coming back. Using recovered alien technology, the nations of Earth have collaborated on an immense defense program to protect the planet. But nothing can prepare us for the aliens’ advanced and unprecedented force. Only the ingenuity of a few brave men and women can bring our world back from the brink of extinction.
            @SerializedName("poster_path")
            val posterPath: String, // /5CHJs479xWnm3zMDOl94VkKS7MZ.jpg
            @SerializedName("release_date")
            val releaseDate: String, // 2016-06-22
            @SerializedName("title")
            val title: String, // Independence Day: Resurgence
            @SerializedName("video")
            val video: Boolean, // false
            @SerializedName("vote_average")
            val voteAverage: Double, // 5.1
            @SerializedName("vote_count")
            val voteCount: Int // 4184
        )
    }
}