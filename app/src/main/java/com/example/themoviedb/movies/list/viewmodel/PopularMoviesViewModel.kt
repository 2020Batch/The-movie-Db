package com.example.themoviedb.movies.list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themoviedb.common.network.model.PopularMovieModel
import com.example.themoviedb.movies.list.model.MdbRepository
import io.reactivex.disposables.CompositeDisposable

class PopularMoviesViewModel(private val repo: MdbRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val searchLiveDataSuccess = MutableLiveData<PopularMovieModel>()
    val searchLiveDataError = MutableLiveData<String>()


    fun getPopularMovies() {
        compositeDisposable.add(
            repo.getPopularMovies().subscribe({ i -> searchLiveDataSuccess.value = i },
                { e -> searchLiveDataError.value = e.message })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}