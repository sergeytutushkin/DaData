package dev.tutushkin.dadata.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.tutushkin.dadata.data.SuggestionsRepository
import dev.tutushkin.dadata.data.SuggestionsRepositoryImpl
import dev.tutushkin.dadata.data.remote.DaDataRemoteDataSource
import dev.tutushkin.dadata.data.remote.DaDataRemoteDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
interface SuggestionsModule {

    @Binds
    fun bindDaDataRemoteDataSource(
        impl: DaDataRemoteDataSourceImpl
    ): DaDataRemoteDataSource

    @Binds
    fun bindSuggestionsRepository(
        impl: SuggestionsRepositoryImpl
    ): SuggestionsRepository
}
