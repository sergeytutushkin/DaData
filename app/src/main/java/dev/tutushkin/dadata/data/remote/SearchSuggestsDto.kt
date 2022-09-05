package dev.tutushkin.dadata.data.remote

import com.google.gson.annotations.SerializedName

data class SearchSuggestsDto(
    @SerializedName("value")
    val value: String,

    @SerializedName("data")
    val data: List<SuggestionDto>
)

data class SuggestionDto(
    @SerializedName("inn")
    val inn: String
)
