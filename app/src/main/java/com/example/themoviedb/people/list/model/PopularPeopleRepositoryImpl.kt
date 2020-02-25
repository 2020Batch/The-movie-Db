package com.example.themoviedb.people.list.model

import com.example.themoviedb.common.API_KEY
import com.example.themoviedb.common.network.RetrofitFactory
import com.example.themoviedb.common.network.model.PopularPeopleModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PopularPeopleRepositoryImpl : PopularPeopleRepository {

    override fun getPopularPeople(): Single<PopularPeopleModel> {
        return RetrofitFactory
            .tmdbClient
            .getPopularPeople(API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}