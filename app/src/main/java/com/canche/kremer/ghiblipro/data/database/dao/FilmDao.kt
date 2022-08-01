package com.canche.kremer.ghiblipro.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.canche.kremer.ghiblipro.data.database.entities.FilmEntity
import com.canche.kremer.ghiblipro.domain.models.Film

@Dao
interface FilmDao {

    @Query("SELECT * FROM film_table")
    suspend fun getAllFilms(): List<FilmEntity>

    @Query("DELETE FROM film_table")
    suspend fun deleteAllFilms()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllFilms(films: List<FilmEntity>)

    @Query("SELECT * FROM film_table WHERE film_table.title || film_table.release_date like '%' || :string || '%'")
    suspend fun getFilmsByTitleOrYear(string: String): List<FilmEntity>
}