package com.canche.kremer.ghiblipro.data.network

import com.canche.kremer.ghiblipro.data.model.FilmModel
import retrofit2.http.GET

interface GhibliApi {

    @GET("films/")
    suspend fun getAllMovies():List<FilmModel>

}