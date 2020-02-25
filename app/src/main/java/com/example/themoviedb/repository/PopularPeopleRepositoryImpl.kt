package com.example.themoviedb.repository

import com.example.themoviedb.common.API_KEY
import com.example.themoviedb.model.PopularPeopleResponse
import com.example.themoviedb.network.ClientInstance
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PopularPeopleRepositoryImpl : PopularPeopleRepository {
    override fun getPopularPeople(): Observable<PopularPeopleResponse> {
        val call = ClientInstance.getClientInstance().getPopularPeople(API_KEY)

        return call.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}