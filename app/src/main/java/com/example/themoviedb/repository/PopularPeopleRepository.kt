package com.example.themoviedb.repository

import com.example.themoviedb.model.PopularPeopleResponse
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single

interface PopularPeopleRepository {
    fun getPopularPeople():Single<PopularPeopleResponse>
}