package com.example.themoviedb.movies.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.themoviedb.movies.detail.model.MovieDetailRepositoryInterface

class MovieDetailViewModelFactory(
    private val repository: MovieDetailRepositoryInterface
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailViewModel(repository) as T
    }
}