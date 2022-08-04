package com.canche.kremer.ghiblipro.domain.usecases

import com.canche.kremer.ghiblipro.data.network.NetworkResult
import com.canche.kremer.ghiblipro.domain.models.Film
import com.canche.kremer.ghiblipro.domain.repository.FilmRepository
import com.canche.kremer.ghiblipro.mockedNetworkResultError
import com.canche.kremer.ghiblipro.mockedNetworkResultException
import com.canche.kremer.ghiblipro.mockedNetworkResultSuccess
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetFilmsUseCaseTest{

    @Mock
    private lateinit var filmRepository: FilmRepository
    lateinit var getFilmsUseCase: GetFilmsUseCase

    @Before
    fun onBefore(){
        getFilmsUseCase = GetFilmsUseCase(filmRepository)
    }

    @Test
    fun `getFilmsUseCase fromApi calls repository getAllFilmsFromApi`() {
        runBlocking {
            getFilmsUseCase.fromApi()
            verify(filmRepository, times(1)).getAllFilmsFromApi()
        }
    }

    @Test
    fun `getFilmsUseCase byID calls repository getAllFilmsFromDB`(){ runBlocking {
        getFilmsUseCase.fromDB()
        verify(filmRepository, times(1)).getAllFilmsFromDB()
        }
    }

    @Test
    fun `getFilmsUseCase byID calls repository getFilmById`(){
        runBlocking {
            val id = "id"
            getFilmsUseCase.byId(id)
            verify(filmRepository, times(1)).getFilmById(id)
        }
    }

}