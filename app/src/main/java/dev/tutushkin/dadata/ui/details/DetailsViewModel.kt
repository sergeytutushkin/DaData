package dev.tutushkin.dadata.ui.details

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.tutushkin.dadata.data.SuggestionsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val suggestionsRepository: SuggestionsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _details = MutableLiveData<DetailsUiState?>()
    val details: LiveData<DetailsUiState?> = _details

    private val args = DetailsFragmentArgs.fromSavedStateHandle(savedStateHandle)

    init {
        onLoadDetails()
    }

    fun onLoadDetails() {
        _details.postValue(DetailsUiState.Loading)

        viewModelScope.launch {
            _details.postValue(loadDetails())
        }
    }

    private suspend fun loadDetails(): DetailsUiState {
        val resultParty = suggestionsRepository.getPartyById(args.inn)
        return if (resultParty.isSuccess) {
            DetailsUiState.SuccessResult(resultParty.getOrThrow())
        } else {
            DetailsUiState.ErrorResult(resultParty.exceptionOrNull())
        }
    }

    fun onReload() {
        _details.postValue(DetailsUiState.Reload)
    }
}
