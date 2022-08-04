package com.canche.kremer.ghiblipro.domain.usecases

import com.canche.kremer.ghiblipro.domain.models.Film
import com.canche.kremer.ghiblipro.domain.repository.FilmRepository
import com.canche.kremer.ghiblipro.mockedFilm
import com.canche.kremer.ghiblipro.mockedFilmList
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchFilmUseCaseTest{

    @Mock
    private lateinit var filmRepository: FilmRepository
    lateinit var searchFilmUseCase: SearchFilmUseCase

    @Before
    fun onBefore(){
        searchFilmUseCase = SearchFilmUseCase(filmRepository)
    }

    @Test
    fun `invoke calls films repository`(){
        runBlocking {
        val wordToSearch = "Castle"

        searchFilmUseCase.invoke(wordToSearch)

        verify(filmRepository, times(1)).getFilmsByTitleOrYear(wordToSearch)
        }
    }

}