package com.example.themoviedb.people.details.model

import com.example.themoviedb.common.API_KEY
import com.example.themoviedb.common.network.RetrofitFactory
import com.example.themoviedb.common.network.model.PersonDetailModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PersonDetailsRepositoryImpl: PersonDetailsRepository {
    override fun getPersonDetails(personId:Int): Single<PersonDetailModel> {
        return RetrofitFactory.tmdbClient.getPersonDetail(personId, API_KEY)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}