package com.example.themoviedb.feature07

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PopularMoviesViewModelFactory(private val repo:MDBRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PopularMoviesViewModel(repo) as T

    }
}