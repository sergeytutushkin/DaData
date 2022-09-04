package dev.tutushkin.dadata.data.remote

import com.google.gson.annotations.SerializedName

data class SearchSuggestsResponse(
    @SerializedName("suggestions")
    val suggestions: List<SearchSuggestsDto>
)
