package dev.tutushkin.dadata.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.tutushkin.dadata.data.SuggestionsRepository
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val suggestionsRepository: SuggestionsRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _details = MutableLiveData<DetailsUiState>()
    val details: LiveData<DetailsUiState> = _details

    init {
        val args = DetailsFragmentArgs.fromSavedStateHandle(savedStateHandle)
    }
}
