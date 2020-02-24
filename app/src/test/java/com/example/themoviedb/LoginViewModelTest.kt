package com.example.themoviedb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.themoviedb.loginvalidation.LoginViewModel
import com.example.themoviedb.loginvalidation.SharedPreferencesRepository
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @Rule
    @JvmField
    val testSchedulerRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: SharedPreferencesRepository

    private lateinit var viewModel: LoginViewModel

}