package dev.tutushkin.dadata.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface DaDataApi {

    @GET("/suggest/party")
    suspend fun getSuggestions(
        @Query("query") query: String
    ): SearchSuggestsResponse

    @GET("/findById/party")
    suspend fun getPartyById(
        @Query("query") query: String,
        @Query("count") count: Int = 1
    ): SuggestsResponse
}
