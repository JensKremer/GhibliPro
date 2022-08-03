package com.canche.kremer.ghiblipro.domain.usecases

import com.canche.kremer.ghiblipro.data.database.entities.FilmEntity
import com.canche.kremer.ghiblipro.data.database.entities.toDatabase
import com.canche.kremer.ghiblipro.data.network.NetworkResult
import com.canche.kremer.ghiblipro.domain.models.Film
import com.canche.kremer.ghiblipro.domain.repository.FilmRepository
import javax.inject.Inject

class SaveFilmsUseCase @Inject constructor(private val repository: FilmRepository) {
    suspend operator fun invoke(films: List<Film>): List<Long>{
       return repository.saveAllFilms(films)
    }
}