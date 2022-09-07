package dev.tutushkin.dadata.data.remote

import javax.inject.Inject

interface DaDataRemoteDataSource {

    suspend fun getSuggestions(query: String): Result<List<SearchSuggestsDto>>

    suspend fun getPartyById(query: String): PartyDto
}

class DaDataRemoteDataSourceImpl @Inject constructor(
    private val daDataApi: DaDataApi
) : DaDataRemoteDataSource {

    override suspend fun getSuggestions(query: String) = runCatching {
        daDataApi.getSuggestions(query).suggestions
    }

    override suspend fun getPartyById(query: String) =
        daDataApi.getPartyById(query).suggestions.first().data.first()
}
