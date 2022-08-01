package com.canche.kremer.ghiblipro.domain.usecases

import android.util.Log
import com.canche.kremer.ghiblipro.data.database.entities.toDatabase
import com.canche.kremer.ghiblipro.data.network.NetworkResult
import com.canche.kremer.ghiblipro.domain.models.Film
import com.canche.kremer.ghiblipro.domain.repository.FilmRepository
import javax.inject.Inject

class GetFilmsUseCase @Inject constructor(private val repository: FilmRepository)
{

   suspend operator fun invoke(): NetworkResult<List<Film>> {

        val films = repository.getAllFilmsFromApi()
        when(films){
            is NetworkResult.Success -> repository.saveAllFilms(films.data.map { it.toDatabase() })
            is NetworkResult.Error -> Log.d("Api Error: ${films.code}", films.message.toString())
            is NetworkResult.Exception -> Log.d("Api Error", films.e.message.toString())
        }

        return films
    }

}