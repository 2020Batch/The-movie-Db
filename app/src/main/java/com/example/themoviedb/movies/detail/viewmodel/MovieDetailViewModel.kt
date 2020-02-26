package com.example.themoviedb.movies.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themoviedb.movies.detail.model.MovieDetailRepositoryInterface
import com.example.themoviedb.common.network.model.MovieDetail
import com.example.themoviedb.common.network.model.SimilarMovieModel
import io.reactivex.disposables.CompositeDisposable

class MovieDetailViewModel(
    private val repository: MovieDetailRepositoryInterface
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val movieDetail: LiveData<MovieDetail>
        get() = mMovieDetail
    private val mMovieDetail = MutableLiveData<MovieDetail>()

    val errorMessage: LiveData<String>
        get() = mErrorMessage
    private val mErrorMessage = MutableLiveData<String>()

    val similarMovies : LiveData<SimilarMovieModel>
        get() = mSimilarMovies
    private val mSimilarMovies = MutableLiveData<SimilarMovieModel>()

    val similarMoviesError : LiveData<String>
        get() = mSimilarMoviesError
    private val mSimilarMoviesError = MutableLiveData<String>()

    fun getSimilarMovies(movieId: Int) {
        compositeDisposable.add(
            repository
                .downloadSimilarMovies(movieId)
                .subscribe(this::handleSimilarMovieData, this::handleSimilarMovieError)
        )
    }

    fun getMovieDetail(movieId: Int) {
        compositeDisposable.add(
            repository
                .downloadMovieDetail(movieId)
                .subscribe(this::handleData, this::handleError)
        )
    }

    private fun handleSimilarMovieData(similarMovieModel: SimilarMovieModel){
        mSimilarMovies.value = similarMovieModel
    }

    private fun handleSimilarMovieError(t:Throwable){
        mSimilarMoviesError.value = t.message
    }

    private fun handleData(movieDetail: MovieDetail) {
        mMovieDetail.value = movieDetail
    }

    private fun handleError(t: Throwable) {
        mErrorMessage.value = t.message
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}