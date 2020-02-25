package com.example.themoviedb.people.list.model

import com.example.themoviedb.common.network.model.PopularPeopleModel
import io.reactivex.Single

interface PopularPeopleRepository {
    fun getPopularPeople():Single<PopularPeopleModel>
}