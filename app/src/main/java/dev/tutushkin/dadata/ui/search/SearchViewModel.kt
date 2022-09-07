package dev.tutushkin.dadata.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.tutushkin.dadata.data.SuggestionsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val suggestionsRepository: SuggestionsRepository
) : ViewModel() {

    private val _suggestions = MutableLiveData<SearchUiState>()
    val suggestions: LiveData<SearchUiState> = _suggestions

    fun onNewQuery(query: String) {
        viewModelScope.launch {
            _suggestions.postValue(handleQuery(query))
        }
    }

    private suspend fun handleQuery(query: String): SearchUiState {
        return if (query.isEmpty()) {
            SearchUiState.EmptyQuery
        } else {
            handleSearchSuggests(query)
        }
    }

    private suspend fun handleSearchSuggests(query: String): SearchUiState {
        val suggestionsResult = suggestionsRepository.getSuggestions(query)
        return if (suggestionsResult.isSuccess) {
            SearchUiState.SuccessResult(suggestionsResult.getOrThrow())
        } else {
            SearchUiState.ErrorResult(IllegalArgumentException("Search suggestions from server error!"))
        }
    }
}
