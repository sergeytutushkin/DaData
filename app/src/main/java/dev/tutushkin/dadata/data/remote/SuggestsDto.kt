package dev.tutushkin.dadata.data.remote

import com.google.gson.annotations.SerializedName

data class SuggestsDto(
    @SerializedName("data")
    val data: List<PartyDto>
)

data class PartyDto(
    @SerializedName("inn")
    val inn: String,

    @SerializedName("name")
    val name: NameDto,

    @SerializedName("address")
    val address: AddressDto
)

data class NameDto(
    @SerializedName("full_with_opf")
    val fullWithOpf: String
)

data class AddressDto(
    @SerializedName("value")
    val value: String
)
