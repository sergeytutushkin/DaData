package dev.tutushkin.dadata.data

import dev.tutushkin.dadata.data.remote.DaDataRemoteDataSource
import dev.tutushkin.dadata.domain.models.Details
import dev.tutushkin.dadata.domain.models.Search
import javax.inject.Inject

interface SuggestionsRepository {

    suspend fun getSuggestions(query: String): Result<List<Search>>

    suspend fun getPartyById(query: String, count: Int): Details
}

class SuggestionsRepositoryImpl @Inject constructor(
    private val daDataRemoteDataSource: DaDataRemoteDataSource
) : SuggestionsRepository {

    override suspend fun getSuggestions(query: String): Result<List<Search>> =
        runCatching {
            daDataRemoteDataSource.getSuggestions(query)
                .getOrThrow()
                .map {
                    Search(
                        value = it.value,
                        inn = it.data.inn
                    )
                }
        }

    override suspend fun getPartyById(query: String, count: Int): Details {
        val details = daDataRemoteDataSource.getPartyById(query)
        return Details(
            inn = details.inn,
            name = details.name.fullWithOpf,
            address = details.address.value
        )
    }
}
