package com.stopstone.sunflower.di

import com.stopstone.sunflower.data.repository.movie.MovieRepository
import com.stopstone.sunflower.data.repository.movie.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun provideMovieRepository(movieRepositoryImpl: MovieRepositoryImpl) : MovieRepository
}