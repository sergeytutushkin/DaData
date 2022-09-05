package dev.tutushkin.dadata.data.remote

import com.google.gson.annotations.SerializedName

data class SuggestsResponse(
    @SerializedName("suggestions")
    val suggestions: List<SuggestsDto>
)
