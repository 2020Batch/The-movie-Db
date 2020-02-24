package com.example.themoviedb.feature07

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PopularMoviesViewModelFactory(val repo:MoviesRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PopularMoviesViewModel(repo) as T

    }
}