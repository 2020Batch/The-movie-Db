package com.example.themoviedb.people.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.themoviedb.people.list.model.PopularPeopleRepository

@Suppress("UNCHECKED_CAST")
class PopularPeopleViewModelFactory(private val repository: PopularPeopleRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PopularPeopleViewModel(
            repository
        ) as T
    }
}