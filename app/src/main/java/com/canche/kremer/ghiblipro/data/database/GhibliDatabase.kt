package com.canche.kremer.ghiblipro.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.canche.kremer.ghiblipro.data.database.dao.FilmDao
import com.canche.kremer.ghiblipro.data.database.entities.FilmEntity

@Database(entities = [FilmEntity::class], version = 1)
    abstract class GhibliDataBase: RoomDatabase() {

        abstract fun getFilmDao():FilmDao

}