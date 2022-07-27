package com.canche.kremer.ghiblipro.data

import com.canche.kremer.ghiblipro.data.model.FilmModel
import com.canche.kremer.ghiblipro.data.network.GhibliApi
import com.canche.kremer.ghiblipro.data.network.NetworkResult
import com.canche.kremer.ghiblipro.domain.models.Film
import com.canche.kremer.ghiblipro.domain.models.toDomain
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class FilmRepository @Inject constructor(
    private val api: GhibliApi
){

    suspend fun <T : Any> handleApi(execute: suspend () -> Response<T>): NetworkResult<T> {
        return try {
            val response = execute()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                NetworkResult.Success(body)
            } else {
                NetworkResult.Error(code = response.code(), message = response.message())
            }
        } catch (e: HttpException) {
            NetworkResult.Error(code = e.code(), message = e.message())
        } catch (e: Throwable) {
            NetworkResult.Exception(e)
        }
    }

    suspend fun getAllFilmsFromApi(): NetworkResult<List<FilmModel>>{
        //val response: List<Film> = api.getAllMovies().body()?.map { it.toDomain() } ?: emptyList()
        return handleApi { api.getAllMovies() }
    }


}