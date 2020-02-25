package com.example.themoviedb.view_model_factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.themoviedb.repository.PopularPeopleRepository
import com.example.themoviedb.view_model.PopularPeopleViewModel

@Suppress("UNCHECKED_CAST")
class PopularPeopleViewModelFactory(private val repository: PopularPeopleRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PopularPeopleViewModel(repository) as T
    }
}