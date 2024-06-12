package com.stopstone.sunflower.di

import com.stopstone.sunflower.data.source.DataSource
import com.stopstone.sunflower.data.source.LocalDataSource
import com.stopstone.sunflower.data.source.RemoteDataSource
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
    fun provideMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository

    @Binds
    @Singleton
    fun provideLocalDataSource(localDataSource: LocalDataSource): DataSource

    @Binds
    @Singleton
    fun provideRemoteDataSource(remoteDataSource: RemoteDataSource): DataSource

}