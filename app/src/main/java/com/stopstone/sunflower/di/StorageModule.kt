package com.stopstone.sunflower.di

import com.stopstone.sunflower.storage.Storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object StorageModule {
    @Provides
    @Singleton
    fun provideStorage(): Storage {
        return Storage
    }
}