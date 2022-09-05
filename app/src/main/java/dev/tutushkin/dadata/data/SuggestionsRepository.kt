package dev.tutushkin.dadata.data

import dev.tutushkin.dadata.data.remote.DaDataRemoteDataSource
import dev.tutushkin.dadata.data.remote.PartyDto
import dev.tutushkin.dadata.data.remote.SearchSuggestsDto
import javax.inject.Inject

// TODO Make a mapper DTO to a domain model
interface SuggestionsRepository {

    suspend fun getSuggestions(query: String): List<SearchSuggestsDto>

    suspend fun getPartyById(query: String, count: Int): PartyDto
}

class SuggestionsRepositoryImpl @Inject constructor(
    private val daDataRemoteDataSource: DaDataRemoteDataSource
) : SuggestionsRepository {

    override suspend fun getSuggestions(query: String) =
        daDataRemoteDataSource.getSuggestions(query)

    override suspend fun getPartyById(query: String, count: Int) =
        daDataRemoteDataSource.getPartyById(query)
}
