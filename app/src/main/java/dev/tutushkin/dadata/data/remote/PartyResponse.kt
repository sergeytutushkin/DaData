package dev.tutushkin.dadata.data.remote

import com.google.gson.annotations.SerializedName

data class PartyResponse(
    @SerializedName("suggestions")
    val suggestions: List<PartyDto>
)
