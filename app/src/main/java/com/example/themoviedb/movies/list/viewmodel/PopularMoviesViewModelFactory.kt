package com.example.themoviedb.movies.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.themoviedb.movies.list.model.MdbRepository

class PopularMoviesViewModelFactory(private val repo: MdbRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PopularMoviesViewModel(repo) as T

    }
}