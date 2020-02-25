package com.example.themoviedb

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.themoviedb.login.viewmodel.LoginRegistrationViewModel
import com.example.themoviedb.login.model.SharedPreferencesRepository
import io.reactivex.Single
import okhttp3.Credentials
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @Rule
    @JvmField
    val testSchedulerRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: SharedPreferencesRepository

    @Mock
    private lateinit var verificationDataObserver : Observer<Boolean>

    private lateinit var model: LoginRegistrationViewModel //Test Subject

    private val registeredCredentials: String = Credentials.basic("Arty", "123")

    private val incorrectCredentials: String = Credentials.basic("Lloyd", "321")

    @Before
    fun setup(){

        model =
            LoginRegistrationViewModel(
                repository,
                Application()
            )

        model.getVerificationLiveData().observeForever(verificationDataObserver)

    }

    @Test
    fun `when repository is called and  the data is found in the database`() {

        //Given
        Mockito.`when`(repository.getCredentials(registeredCredentials)).thenReturn(Single.just(true))

        //When
        model.credentialsVerification("Arty", "123")

        //Then
        Mockito.verify(verificationDataObserver).onChanged(true)
        Assert.assertTrue(model.getVerificationLiveData().value!!)

    }

    @Test
    fun `when repository is called and the data is not found in the database, Livedata returns false`(){

        //Given
        Mockito.`when`(repository.getCredentials(incorrectCredentials)).thenReturn(Single.just(false))

        //When
        model.credentialsVerification("Lloyd", "321")

        //Then
       Mockito.verify(verificationDataObserver).onChanged(false)
        Assert.assertFalse(model.getVerificationLiveData().value!!)

    }
}