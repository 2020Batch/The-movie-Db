package com.example.themoviedb.feature07

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themoviedb.feature07.model.PopularMovieModel
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