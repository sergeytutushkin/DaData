package dev.tutushkin.dadata.ui.details

import dev.tutushkin.dadata.domain.models.Details

sealed class DetailsUiState {
    object NotLogged : DetailsUiState()
    object Loading : DetailsUiState()
    object Reload : DetailsUiState()
    data class SuccessResult(val result: Details) : DetailsUiState()
    data class ErrorResult(val e: Throwable?) : DetailsUiState()
}
