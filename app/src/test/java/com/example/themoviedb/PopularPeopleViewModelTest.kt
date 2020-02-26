package com.example.themoviedb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.themoviedb.common.network.model.Person
import com.example.themoviedb.common.network.model.PopularPeopleModel
import com.example.themoviedb.people.list.model.PopularPeopleRepository
import com.example.themoviedb.people.list.viewmodel.PopularPeopleViewModel
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PopularPeopleViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: PopularPeopleRepository

    private lateinit var viewModelTest: PopularPeopleViewModel

    @Mock
    private lateinit var popularPeopleObserver: Observer<PopularPeopleModel>

    @Mock
    private lateinit var fetchErrorObserver: Observer<String>

    private lateinit var model: PopularPeopleModel

    @Before
    fun setup() {
        val person = Person("TAE", 93.4, "image.png")
        viewModelTest = PopularPeopleViewModel(repository)
        viewModelTest.getPopularPeople().observeForever(popularPeopleObserver)
        viewModelTest.getFetchError().observeForever(fetchErrorObserver)
        model = PopularPeopleModel(listOf(person))
    }

    @Test
    fun `fetch Popular People call Successfully`() {
        //Given
        `when`(repository.getPopularPeople()).thenReturn(Single.just(model))

        //When
        viewModelTest.fetchPopularPeople()

        //Then
        Assert.assertEquals(model, viewModelTest.getPopularPeople().value)
        verify(popularPeopleObserver).onChanged(model)
    }

    @Test
    fun `fetch Popular People call UnSuccessful`() {
        //Given
        val error = "error"
        `when`(repository.getPopularPeople()).thenReturn(Single.error(NullPointerException(error)))

        //When
        viewModelTest.fetchPopularPeople()

        //Then
        Assert.assertEquals(error, viewModelTest.getFetchError().value)
        verify(fetchErrorObserver).onChanged(error)
    }
}