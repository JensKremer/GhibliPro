package com.canche.kremer.ghiblipro.ui.viewmodel

import androidx.arch.core.executor.testing.CountingTaskExecutorRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.canche.kremer.ghiblipro.CoroutinesTestRule
import com.canche.kremer.ghiblipro.domain.models.Film
import com.canche.kremer.ghiblipro.domain.usecases.GetFilmsUseCase
import com.canche.kremer.ghiblipro.domain.usecases.SearchFilmUseCase
import com.canche.kremer.ghiblipro.mockedFilm
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FilmViewModelTest{

//    @get:Rule
//    val rule = CoroutinesTestRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getFilmsUseCase: GetFilmsUseCase

    @Mock
    lateinit var observer: Observer<Film>

    private lateinit var vm: FilmViewModel

    @Before
    fun onBefore(){
        vm = FilmViewModel(getFilmsUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun `use get fil by id changes the value of film`() = runBlocking{
        val  film = mockedFilm
        whenever(getFilmsUseCase.byId("1")).thenReturn(film)

        vm.film.observeForever(observer)
        vm.getFilmById("1")

        verify(observer).onChanged(film)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

}