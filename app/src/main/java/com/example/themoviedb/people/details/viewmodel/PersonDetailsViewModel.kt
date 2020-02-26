package com.example.themoviedb.people.details.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themoviedb.common.network.model.PersonDetailModel
import com.example.themoviedb.people.details.model.PersonDetailsRepository
import io.reactivex.disposables.CompositeDisposable

class PersonDetailsViewModel(private val personDetailsRepository: PersonDetailsRepository) :
    ViewModel() {
    val repositoryPersonDetail = MutableLiveData<PersonDetailModel>()
    val errorPersonDetail = MutableLiveData<String>()
    private val compositeDisposable = CompositeDisposable()


    fun fetchPersonDetail(personId: Int) {
        compositeDisposable.add(personDetailsRepository.getPersonDetails(personId).subscribe({ personDetails ->
            repositoryPersonDetail.value = personDetails
        }, { errorFetch ->
            errorPersonDetail.value = errorFetch.message
        }))

    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}