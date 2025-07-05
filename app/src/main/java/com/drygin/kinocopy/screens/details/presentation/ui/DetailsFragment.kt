package com.drygin.kinocopy.screens.details.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.drygin.kinocopy.R
import com.drygin.kinocopy.common.theme.KinoCopyTheme
import com.drygin.kinocopy.databinding.FragmentDetailsBinding
import com.drygin.kinocopy.screens.details.presentation.viewmodel.DetailsViewModel
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Drygin Nikita on 05.07.2025.
 */
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val viewModel: DetailsViewModel by viewModel()

    private var _binding: FragmentDetailsBinding? = null
    val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentDetailsBinding.bind(view)

        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.film.filterNotNull().collect { film ->
                    binding.topAppBar.setTitle(film.name)
                }
            }
        }

        binding.composeView.setContent {
            KinoCopyTheme {
                DetailsScreen(viewModel = viewModel)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}