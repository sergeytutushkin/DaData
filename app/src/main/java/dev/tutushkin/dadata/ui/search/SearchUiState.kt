package dev.tutushkin.dadata.ui.search

import dev.tutushkin.dadata.domain.models.Search

sealed class SearchUiState {
    object NotLogged : SearchUiState()
    object EmptyQuery : SearchUiState()
    object EmptyResult : SearchUiState()
    object TextChanged : SearchUiState()
    data class SuccessResult(val result: List<Search>) : SearchUiState()
    data class ErrorResult(val e: Throwable) : SearchUiState()
    data class SelectSuggestion(val suggestion: Search) : SearchUiState()
}
