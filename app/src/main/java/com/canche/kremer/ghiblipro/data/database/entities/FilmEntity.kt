package com.canche.kremer.ghiblipro.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.canche.kremer.ghiblipro.data.model.FilmModel
import com.canche.kremer.ghiblipro.domain.models.Film


@Entity(tableName = "film_table", indices = [Index(value = ["title"], unique = true)])
data class FilmEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "originalTitle") val originalTitle: String,
    @ColumnInfo(name = "original_title_romanised") val originalTitleRomanised: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "movie_banner") val movieBanner: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "director") val director: String,
    @ColumnInfo(name = "producer") val producer: String,
    @ColumnInfo(name = "release_date") val releaseDate: Int,
    @ColumnInfo(name = "running_time") val runningTime: Int,
    @ColumnInfo(name = "rt_score") val rtScore: Int,
    )

fun Film.toDatabase() = FilmEntity(
    id = id,
    title = title,
    originalTitle = originalTitle,
    originalTitleRomanised = originalTitleRomanised,
    image = image,
    movieBanner = movieBanner,
    description = description,
    director = director,
    producer = producer,
    releaseDate = releaseDate,
    runningTime = runningTime,
    rtScore = rtScore,
)

fun FilmModel.toDatabase() = FilmEntity(
    id = id,
    title = title,
    originalTitle = originalTitle,
    originalTitleRomanised = originalTitleRomanised,
    image = image,
    movieBanner = movieBanner,
    description = description,
    director = director,
    producer = producer,
    releaseDate = releaseDate,
    runningTime = runningTime,
    rtScore = rtScore,
)
