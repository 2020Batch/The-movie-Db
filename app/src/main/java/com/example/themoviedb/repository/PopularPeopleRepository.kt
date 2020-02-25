package com.example.themoviedb.repository

import com.example.themoviedb.model.PopularPeopleResponse
import io.reactivex.Observable
import io.reactivex.Observer

interface PopularPeopleRepository {
    fun getPopularPeople():Observable<PopularPeopleResponse>
}