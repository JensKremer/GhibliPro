package com.canche.kremer.ghiblipro.domain.repository

import com.canche.kremer.ghiblipro.data.network.NetworkResult
import com.canche.kremer.ghiblipro.domain.models.Film

interface FilmRepository {

        suspend fun getAllFilmsFromApi(): NetworkResult<List<Film>>

}