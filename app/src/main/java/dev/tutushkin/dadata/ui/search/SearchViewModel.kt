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

    fun onTextChanged(text: String) {
        if (text.isEmpty()) {
            _suggestions.postValue(SearchUiState.EmptyQuery)
        } else {
            _suggestions.postValue(SearchUiState.TextChanged)
        }
    }

    fun onNewQuery(query: String) {
        viewModelScope.launch {
            val state = handleQuery(query)
            _suggestions.postValue(state)
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
            val result = suggestionsResult.getOrThrow()
            if (result.isEmpty()) {
                SearchUiState.EmptyResult
            } else {
                SearchUiState.SuccessResult(result)
            }
        } else {
            SearchUiState.ErrorResult(IllegalArgumentException("Search suggestions from server error!"))
        }
    }

    fun onSelectSuggestion(position: Int) {
        _suggestions.postValue(SearchUiState.SelectSuggestion(position))
    }
}
