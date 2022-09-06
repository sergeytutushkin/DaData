package dev.tutushkin.dadata.ui.search

import dev.tutushkin.dadata.domain.models.Search

data class SearchUiState(
    val errorMessage: String? = null,
    val isUserLoggedIn: Boolean = true,
    val suggestions: List<Search> = emptyList(),
    val selectedSuggestion: Search? = null
)
