package com.example.themoviedb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.themoviedb.common.network.model.Movie
import com.example.themoviedb.common.network.model.PopularMovieModel
import com.example.themoviedb.movies.list.model.MdbRepository
import com.example.themoviedb.movies.list.model.MdbRepositoryImpl
import com.example.themoviedb.movies.list.viewmodel.PopularMoviesViewModel
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.lang.RuntimeException


@RunWith(MockitoJUnitRunner::class)
class PopularMoviesTest {


    @Rule
    @JvmField
    val instantExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MdbRepository


    private lateinit var popMoviesViewModel: PopularMoviesViewModel


    private lateinit var movieModel: Movie


    @Before
    fun setUp(){
        popMoviesViewModel = PopularMoviesViewModel(repository)
        movieModel = Movie(2.3,4,true,"dn23n23ed2.jpg", 2231,true,"ddowj2dojd2","ENGLISH","titleboo",
            listOf(),"titleboo2",2.3,"overvieeew","0123,12312")
    }


    @Test
    fun `searchLiveDataSuccess gets data successfully`(){
        //Given
        val movie = PopularMovieModel(1, 3123, 12, listOf(movieModel))
        `when`(repository.getPopularMovies()).thenReturn(Single.just(movie))

        //When
        popMoviesViewModel.getPopularMovies()

        //Then
        Assert.assertEquals(movie, popMoviesViewModel.searchLiveDataSuccess.value)
    }

    @Test
    fun `searchLiveDataError gets error info successfully`(){
        //Given
        val errorMessage = "error"
        `when`(repository.getPopularMovies()).thenReturn(Single.error(RuntimeException(errorMessage)))

        //When
        popMoviesViewModel.getPopularMovies()

        //Then
        Assert.assertEquals(errorMessage, popMoviesViewModel.searchLiveDataError.value)


    }


}