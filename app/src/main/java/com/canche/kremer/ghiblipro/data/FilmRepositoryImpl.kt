package com.canche.kremer.ghiblipro.data

import com.canche.kremer.ghiblipro.data.model.FilmModel
import com.canche.kremer.ghiblipro.data.network.GhibliApi
import com.canche.kremer.ghiblipro.data.network.NetworkResult
import com.canche.kremer.ghiblipro.domain.models.Film
import com.canche.kremer.ghiblipro.domain.models.mapFilms
import com.canche.kremer.ghiblipro.domain.models.toDomain
import com.canche.kremer.ghiblipro.domain.repository.FilmRepository
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(private val api: GhibliApi): FilmRepository
{
    suspend fun <T : Any, P : Any> handleApi(execute: suspend () -> Response<T>,
                                             map: (T) -> P): NetworkResult<P>
    {
        return try {
            val response = execute()
            val body = response.body().run { this?.let { map(it) } }
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

    override suspend fun getAllFilmsFromApi(): NetworkResult<List<Film>>{
         return handleApi({api.getAllMovies()},{mapFilms(it)})
    }


}