package dev.tutushkin.dadata.ui.details

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.tutushkin.dadata.R
import dev.tutushkin.dadata.databinding.FragmentDetailsBinding
import dev.tutushkin.dadata.domain.models.Details

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val viewModel: DetailsViewModel by viewModels()

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentDetailsBinding.bind(view)

        binding.buttonRetry.setOnClickListener { viewModel.onReload() }

        viewModel.details.observe(viewLifecycleOwner, ::render)
    }

    private fun render(state: DetailsUiState?) = when (state) {
        DetailsUiState.Loading -> showLoading(true)
        DetailsUiState.NotLogged -> {}
        is DetailsUiState.SuccessResult -> renderResult(state.result)
        is DetailsUiState.ErrorResult -> showError(getString(R.string.details_text_status))
        DetailsUiState.Reload -> viewModel.onLoadDetails()
        null -> TODO()
    }

    private fun showLoading(show: Boolean) = with(binding) {
        groupStatus.isVisible = false
        groupOrganizationDetails.isVisible = !show
        progressSpinner.isVisible = show
    }

    private fun renderResult(details: Details) {
        showLoading(false)

        with(binding) {
            textName.text = details.name
            textDetailsInn.text = details.inn
            textAddress.text = details.address
        }
    }

    private fun showError(text: String) = with(binding) {
        progressSpinner.isVisible = false
        groupOrganizationDetails.isVisible = false
        textStatus.text = text
        groupStatus.isVisible = true
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}
