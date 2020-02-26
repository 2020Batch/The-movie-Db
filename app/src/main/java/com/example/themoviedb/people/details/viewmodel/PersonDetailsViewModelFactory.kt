package com.example.themoviedb.people.details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.themoviedb.people.details.model.PersonDetailsRepository

class PersonDetailsViewModelFactory(private val personDetailsRepository: PersonDetailsRepository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PersonDetailsViewModel(personDetailsRepository) as T
    }

}