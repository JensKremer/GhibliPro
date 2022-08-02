package com.canche.kremer.ghiblipro.domain.usecases

import com.canche.kremer.ghiblipro.data.network.NetworkResult
import com.canche.kremer.ghiblipro.domain.models.Film
import com.canche.kremer.ghiblipro.domain.repository.FilmRepository
import com.canche.kremer.ghiblipro.mockedNetworkResultError
import com.canche.kremer.ghiblipro.mockedNetworkResultException
import com.canche.kremer.ghiblipro.mockedNetworkResultSuccess
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
    fun `invoke calls films repository when network result is success`()= runBlocking {
        val networkResultSuccess = mockedNetworkResultSuccess
        whenever(filmRepository.getAllFilmsFromApi()).thenReturn(networkResultSuccess)

        val result = getFilmsUseCase.invoke()

        assertEquals(networkResultSuccess, result)

    }

    @Test
    fun `invoke calls films repository when network result is an Error`()= runBlocking {
        val networkResultError= mockedNetworkResultError
        whenever(filmRepository.getAllFilmsFromApi()).thenReturn(networkResultError)

        val result = getFilmsUseCase.invoke()

        assertEquals(networkResultError, result)

    }

    @Test
    fun `invoke calls films repository when network result is an Exeption`()= runBlocking {
        val networkResultException= mockedNetworkResultException
        whenever(filmRepository.getAllFilmsFromApi()).thenReturn(networkResultException)

        val result = getFilmsUseCase.invoke()

        assertEquals(networkResultException, result)

    }

}