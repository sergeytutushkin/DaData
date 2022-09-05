package dev.tutushkin.dadata.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.tutushkin.dadata.data.SuggestionsRepository
import dev.tutushkin.dadata.data.SuggestionsRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface SuggestionsModule {

    @Binds
    fun bindSuggestionsRepository(
        impl: SuggestionsRepositoryImpl
    ): SuggestionsRepository

}
