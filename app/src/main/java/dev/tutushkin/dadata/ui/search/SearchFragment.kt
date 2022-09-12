package dev.tutushkin.dadata.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.tutushkin.dadata.R
import dev.tutushkin.dadata.databinding.FragmentSearchBinding
import java.util.*

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewModel: SearchViewModel by viewModels()

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var searchAdapter: SearchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentSearchBinding.bind(view)

        searchAdapter = SearchAdapter(requireContext(), R.layout.item_suggestion, mutableListOf())
        binding.listSuggestions.adapter = searchAdapter

        binding.editSearch.addTextChangedListener(textWatcher)

        viewModel.suggestions.observe(viewLifecycleOwner, ::handleSearch)
    }

    private fun handleSearch(state: SearchUiState?) {
        when (state) {
            is SearchUiState.SuccessResult -> {
                val list = state.result.map { it.copy() }
                searchAdapter.addAll(list)
            }
            SearchUiState.EmptyQuery -> Toast.makeText(
                requireContext(),
                "Empty Query",
                Toast.LENGTH_SHORT
            ).show()
            SearchUiState.EmptyResult -> Toast.makeText(
                requireContext(),
                "Empty Result",
                Toast.LENGTH_SHORT
            ).show()
            is SearchUiState.ErrorResult -> Toast.makeText(
                requireContext(),
                "Error Result",
                Toast.LENGTH_SHORT
            ).show()
            is SearchUiState.NewQuery -> Toast.makeText(
                requireContext(),
                "New Query",
                Toast.LENGTH_SHORT
            ).show()
            SearchUiState.NotLogged -> Toast.makeText(
                requireContext(),
                "Not Logged",
                Toast.LENGTH_SHORT
            ).show()
            is SearchUiState.SelectSuggestion -> Toast.makeText(
                requireContext(),
                "Select Suggestion",
                Toast.LENGTH_SHORT
            ).show()
            null -> Toast.makeText(
                requireContext(),
                "null",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private val textWatcher: TextWatcher = object : TextWatcher {
        private var timer = Timer()
        private val DELAY: Long = 500L

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun afterTextChanged(s: Editable?) {
            searchAdapter.clear()

            timer.cancel()
            timer = Timer()
            timer.schedule(object : TimerTask() {
                override fun run() {
                    viewModel.onNewQuery(s.toString())
                }
            }, DELAY)
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
