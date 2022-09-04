package dev.tutushkin.dadata.ui.details

data class DetailsUiState(
    val errorMessage: String? = null,
    val isUserLoggedIn: Boolean = true,
    val isLoading: Boolean = true,
    val details: OrganizationDetails
)

data class OrganizationDetails(
    val inn: String,
    val name: String,
    val address: String
)
