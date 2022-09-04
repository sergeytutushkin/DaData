package dev.tutushkin.dadata

import dev.tutushkin.dadata.data.remote.PartyResponse
import dev.tutushkin.dadata.data.remote.SearchSuggestsResponse

interface SuggestionsRepository {
    suspend fun getSuggestions(query: String): SearchSuggestsResponse
    suspend fun getPartyById(query: String, count: Int): PartyResponse
}
