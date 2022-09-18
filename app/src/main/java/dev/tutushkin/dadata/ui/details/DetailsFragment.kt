package dev.tutushkin.dadata.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.tutushkin.dadata.R
import dev.tutushkin.dadata.databinding.FragmentDetailsBinding

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    val viewModel: DetailsViewModel by viewModels()

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentDetailsBinding.bind(view)

        viewModel.details.observe(viewLifecycleOwner, ::render)
    }

    private fun render(state: DetailsUiState?) {
        TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}
