package com.canche.kremer.ghiblipro.domain.usecases

import com.canche.kremer.ghiblipro.domain.repository.FilmRepository
import com.canche.kremer.ghiblipro.mockedFilmList
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SaveFilmsUseCaseTest{

    @Mock
    private lateinit var filmRepository: FilmRepository
    lateinit var saveFilmsUseCase: SaveFilmsUseCase

    @Before
    fun onBefore(){
        saveFilmsUseCase = SaveFilmsUseCase(filmRepository)
    }


    @Test
    fun `invoke calls films repository`(){
        runBlocking {
            val films = mockedFilmList

            saveFilmsUseCase(films)

            verify(filmRepository, times(1)).saveAllFilms(films)
        }
    }

}