package com.canche.kremer.ghiblipro.di

import com.canche.kremer.ghiblipro.data.FilmRepositoryImpl
import com.canche.kremer.ghiblipro.data.database.dao.FilmDao
import com.canche.kremer.ghiblipro.data.network.GhibliApi
import com.canche.kremer.ghiblipro.domain.repository.FilmRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideFilmRepository(ghibliApi: GhibliApi, fimDao: FilmDao):
            FilmRepository = FilmRepositoryImpl(ghibliApi, fimDao)

}