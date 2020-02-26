package com.example.themoviedb.people.details.model

import com.example.themoviedb.common.network.model.PersonDetailModel
import io.reactivex.Single

interface PersonDetailsRepository {
    fun getPersonDetails(personId: Int): Single<PersonDetailModel>
}