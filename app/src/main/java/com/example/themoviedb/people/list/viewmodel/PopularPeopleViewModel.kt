package com.example.themoviedb.people.list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themoviedb.common.network.model.PopularPeopleModel
import com.example.themoviedb.people.list.model.PopularPeopleRepository
import io.reactivex.disposables.CompositeDisposable

class PopularPeopleViewModel(private val repository: PopularPeopleRepository) : ViewModel() {
    private val popularPeople = MutableLiveData<PopularPeopleModel>()
    private val fetchError = MutableLiveData<String>()
    private val compositeDisposable = CompositeDisposable()

    fun getPopularPeople(): MutableLiveData<PopularPeopleModel> {
        return popularPeople
    }

    fun getFetchError(): MutableLiveData<String> {
        return fetchError
    }

    fun fetchPopularPeople() {

        compositeDisposable.add(
            repository.getPopularPeople().subscribe(
                { person -> popularPeople.value = person },
                { personFetchError -> fetchError.value = personFetchError.message })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}