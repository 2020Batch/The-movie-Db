package com.example.themoviedb.common.network.model

import com.google.gson.annotations.SerializedName

data class PopularPeopleModel(
    @SerializedName("results") val people: List<Person>
)