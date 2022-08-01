package com.canche.kremer.ghiblipro.domain.usecases

import com.canche.kremer.ghiblipro.data.network.NetworkResult
import com.canche.kremer.ghiblipro.domain.models.Film
import com.canche.kremer.ghiblipro.domain.repository.FilmRepository
import javax.inject.Inject

class SearchFilmUseCase @Inject constructor(private val repository: FilmRepository){

    suspend operator fun invoke(string: String): List<Film>{
        return repository.getFilmsByTitleOrYear(string)
    }

}