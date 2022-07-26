package com.canche.kremer.ghiblipro.domain.usecases

import com.canche.kremer.ghiblipro.data.FilmRepository
import com.canche.kremer.ghiblipro.domain.models.Film
import javax.inject.Inject

class GetFilmsUseCase @Inject constructor(private val repository: FilmRepository) {

    suspend operator fun invoke():List<Film>{
        return repository.getAllFilmsFromApi()
    }

}