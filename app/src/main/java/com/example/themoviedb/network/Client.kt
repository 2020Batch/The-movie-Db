package com.example.themoviedb.network

import com.example.themoviedb.common.API_KEY_NAME
import com.example.themoviedb.common.END_POINT_POPULAR_PEOPLE
import com.example.themoviedb.model.PopularPeopleResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Client {
    @GET(END_POINT_POPULAR_PEOPLE)
    fun getPopularPeople(@Query(API_KEY_NAME) api_key: String): Observable<PopularPeopleResponse>
}