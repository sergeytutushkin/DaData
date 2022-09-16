package dev.tutushkin.dadata.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
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

    private fun handleSearch(state: SearchUiState) {
        when (state) {
            is SearchUiState.SuccessResult -> {
                binding.textMessage.visibility = View.INVISIBLE
                val list = state.result.map { it.copy() }
                searchAdapter.addAll(list)
            }

            SearchUiState.TextChanged -> {
                searchAdapter.clear()
                binding.textMessage.visibility = View.INVISIBLE
            }

            SearchUiState.EmptyQuery -> {
                binding.textMessage.visibility = View.VISIBLE
                binding.textMessage.text = getText(R.string.search_message_empty)
            }

            SearchUiState.EmptyResult -> {
                binding.textMessage.visibility = View.VISIBLE
                binding.textMessage.text = getText(R.string.search_message_not_found)
            }

            is SearchUiState.ErrorResult -> {
                binding.textMessage.visibility = View.VISIBLE
                binding.textMessage.text = getText(R.string.search_message_error)
            }

            SearchUiState.NotLogged -> {}

            is SearchUiState.SelectSuggestion -> {}
        }
    }

    private val textWatcher: TextWatcher = object : TextWatcher {
        private var timer = Timer()

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun afterTextChanged(s: Editable?) {
            viewModel.onTextChanged(s.toString())

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

    companion object {
        private const val DELAY = 500L
    }
}
