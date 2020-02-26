package com.example.themoviedb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.themoviedb.common.network.model.MovieDetail
import com.example.themoviedb.mocks.movieDetailMock
import com.example.themoviedb.movies.detail.model.MovieDetailRepositoryInterface
import com.example.themoviedb.movies.detail.viewmodel.MovieDetailViewModel
import io.reactivex.Single
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieDetailViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MovieDetailRepositoryInterface

    private lateinit var viewModel: MovieDetailViewModel

    @Mock
    private lateinit var detailObserver: Observer<MovieDetail>

    @Mock
    private lateinit var errorObserver: Observer<String>

    @Before
    fun setUp() {
        viewModel = MovieDetailViewModel(repository)

        viewModel.movieDetail.observeForever(detailObserver)
        viewModel.errorMessage.observeForever(errorObserver)
    }

    @Test
    fun `getMovieDetail successful call change correctly the value of movieDetail live data`(){
        // Given
        val movieId = 18
        val movieMock = movieDetailMock.copy(id = movieId)
        `when`(repository.downloadMovieDetail(movieId)).thenReturn(Single.just(movieMock))

        // When
        viewModel.getMovieDetail(movieId)

        // Then
        verify(detailObserver).onChanged(movieMock)
        assertEquals(movieMock, viewModel.movieDetail.value!!)
    }

    @Test
    fun `getMovieDetail failed call change correctly the value of errorMessage live data`(){
        // Given
        val movieId = 0
        val errorMessage = "error_message"
        val exception = IllegalStateException(errorMessage)
        `when`(repository.downloadMovieDetail(movieId)).thenReturn(Single.error(exception))

        // When
        viewModel.getMovieDetail(movieId)

        // Then
        verify(errorObserver).onChanged(errorMessage)
        assertEquals(errorMessage, viewModel.errorMessage.value!!)
    }
}