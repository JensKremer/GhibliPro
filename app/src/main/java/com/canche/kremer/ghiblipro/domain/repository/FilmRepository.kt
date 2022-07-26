package com.canche.kremer.ghiblipro.domain.repository

import com.canche.kremer.ghiblipro.data.database.entities.FilmEntity
import com.canche.kremer.ghiblipro.data.network.NetworkResult
import com.canche.kremer.ghiblipro.domain.models.Film

interface FilmRepository {

        suspend fun getAllFilmsFromApi(): NetworkResult<List<Film>>

        suspend fun saveAllFilms(films: List<Film>): List<Long>

        suspend fun getAllFilmsFromDB(): List<Film>

        suspend fun getFilmById(id: String): Film

        suspend fun getFilmsByTitleOrYear(string: String): List<Film>

}