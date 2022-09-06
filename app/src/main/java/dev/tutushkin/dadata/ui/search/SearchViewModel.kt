package dev.tutushkin.dadata.ui.search

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.tutushkin.dadata.data.SuggestionsRepository
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SuggestionsRepository
) : ViewModel() {
    // TODO: Implement the ViewModel
}
