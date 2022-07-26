package com.canche.kremer.ghiblipro.data

import com.canche.kremer.ghiblipro.data.model.FilmModel
import com.canche.kremer.ghiblipro.data.network.GhibliApi
import com.canche.kremer.ghiblipro.domain.models.Film
import com.canche.kremer.ghiblipro.domain.models.toDomain
import javax.inject.Inject

class FilmRepository @Inject constructor(
    private val api: GhibliApi
){

    suspend fun getAllFilmsFromApi(): List<Film>{
        val response: List<FilmModel> = api.getAllMovies()
        return response.map { it.toDomain() }

    }


}