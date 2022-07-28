package com.canche.kremer.ghiblipro.di

import android.content.Context
import androidx.room.Room
import com.canche.kremer.ghiblipro.data.database.GhibliDataBase
import com.canche.kremer.ghiblipro.data.database.dao.FilmDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideFilmDao(ghibliDataBase: GhibliDataBase): FilmDao{
        return ghibliDataBase.getFilmDao()
    }

    @Singleton
    @Provides
    fun provideGhibliDatabase(@ApplicationContext appContext: Context) :
            GhibliDataBase{
        return Room.databaseBuilder(
            appContext,
            GhibliDataBase::class.java,
            "ghibli.db"
        ).build()
    }
}