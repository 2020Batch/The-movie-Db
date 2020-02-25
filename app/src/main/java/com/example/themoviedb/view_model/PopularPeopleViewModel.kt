package com.example.themoviedb.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themoviedb.model.PopularPeopleResponse
import com.example.themoviedb.repository.PopularPeopleRepository
import io.reactivex.disposables.CompositeDisposable

class PopularPeopleViewModel(private val repository: PopularPeopleRepository) : ViewModel() {
    private val popularPeople = MutableLiveData<PopularPeopleResponse>()
    private val fetchError = MutableLiveData<String>()
    private val compositeDisposable = CompositeDisposable()

    fun getPopularPeople(): MutableLiveData<PopularPeopleResponse> {
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