package com.canche.kremer.ghiblipro.domain.usecases

import com.canche.kremer.ghiblipro.domain.repository.FilmRepository
import com.canche.kremer.ghiblipro.mockedFilmList
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
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
    fun `invoke calls films repository`()= runBlocking {
        val films = mockedFilmList
        whenever(filmRepository.saveAllFilms(films)).thenReturn(listOf(1L))
        val result = saveFilmsUseCase.invoke(films)

        assertTrue(result.isNotEmpty())
        assertEquals(result, listOf(1L))

    }


}