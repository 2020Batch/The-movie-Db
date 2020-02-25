package com.example.themoviedb.moviedetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.themoviedb.moviedetail.model.MovieDetailRepositoryInterface

class MovieDetailViewModelFactory(
    private val repository: MovieDetailRepositoryInterface
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailViewModel(repository) as T
    }
}