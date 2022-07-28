package com.canche.kremer.ghiblipro.domain.models

import com.canche.kremer.ghiblipro.data.database.entities.FilmEntity
import com.canche.kremer.ghiblipro.data.model.FilmModel
import com.google.gson.annotations.SerializedName

data class Film (val title: String,
                 val originalTitle: String,
                 val originalTitleRomanised: String,
                 val image: String,
                 val movieBanner: String,
                 val description: String,
                 val director: String,
                 val producer: String,
                 val releaseDate: Int,
                 val runningTime: Int,
                 val rtScore: Int,)

fun FilmModel.toDomain() = Film(title, originalTitle, originalTitleRomanised, image, movieBanner, description, director, producer, releaseDate, runningTime, rtScore)
fun FilmEntity.toDomain() = Film(title, originalTitle, originalTitleRomanised, image, movieBanner, description, director, producer, releaseDate, runningTime, rtScore)

fun mapFilms(listModel : List<FilmModel>): List<Film>{
    return listModel.map {it.toDomain() }
}