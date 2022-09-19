package dev.tutushkin.dadata.data

import dev.tutushkin.dadata.data.remote.DaDataRemoteDataSource
import dev.tutushkin.dadata.domain.models.Details
import dev.tutushkin.dadata.domain.models.Search
import javax.inject.Inject

interface SuggestionsRepository {

    suspend fun getSuggestions(query: String): Result<List<Search>>

    suspend fun getPartyById(query: String): Result<Details>
}

class SuggestionsRepositoryImpl @Inject constructor(
    private val daDataRemoteDataSource: DaDataRemoteDataSource
) : SuggestionsRepository {

    override suspend fun getSuggestions(query: String) = runCatching {
        daDataRemoteDataSource.getSuggestions(query)
            .getOrThrow()
            .map {
                Search(
                    value = it.value,
                    inn = it.data.inn
                )
            }
    }

    override suspend fun getPartyById(query: String) =
        daDataRemoteDataSource.getPartyById(query)
            .mapCatching {
                Details(
                    inn = it.inn,
                    name = it.name.fullWithOpf,
                    address = it.address.value
                )
            }
}
