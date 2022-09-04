package dev.tutushkin.dadata.ui.search

data class SearchUiState(
    val errorMessage: String? = null,
    val isUserLoggedIn: Boolean = true,
    val suggestions: List<Suggestion> = emptyList(),
    val selectedSuggestion: Suggestion? = null
)

data class Suggestion(
    val value: String,
    val inn: String
)
