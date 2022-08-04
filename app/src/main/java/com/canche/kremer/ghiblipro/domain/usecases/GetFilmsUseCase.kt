package com.canche.kremer.ghiblipro.domain.usecases

import com.canche.kremer.ghiblipro.data.network.NetworkResult
import com.canche.kremer.ghiblipro.domain.models.Film
import com.canche.kremer.ghiblipro.domain.repository.FilmRepository
import javax.inject.Inject

class GetFilmsUseCase @Inject constructor(private val repository: FilmRepository) {
    suspend fun fromApi(): NetworkResult<List<Film>> = repository.getAllFilmsFromApi()
    suspend fun fromDB(): List<Film> = repository.getAllFilmsFromDB()
    suspend fun byId(id: String):Film = repository.getFilmById(id)
}