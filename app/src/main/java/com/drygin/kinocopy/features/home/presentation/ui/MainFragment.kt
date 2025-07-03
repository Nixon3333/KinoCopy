package com.drygin.kinocopy.features.home.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.drygin.kinocopy.R
import com.drygin.kinocopy.common.ui.GridItemDecoration
import com.drygin.kinocopy.databinding.FragmentMainBinding
import com.drygin.kinocopy.features.home.presentation.MainFragmentEvent
import com.drygin.kinocopy.features.home.presentation.MainFragmentViewModel
import com.drygin.kinocopy.features.home.presentation.adapter.FilmAdapter
import com.drygin.kinocopy.features.home.presentation.adapter.GenreAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Drygin Nikita on 02.07.2025.
 */
class MainFragment : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainFragmentViewModel by viewModel()

    private lateinit var genreAdapter: GenreAdapter
    private lateinit var filmAdapter: FilmAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)

        setupRecyclerViews()
        observeState()
    }

    private fun setupRecyclerViews() {
        val spacing = resources.getDimensionPixelSize(R.dimen.spacing_medium)
        val edgeSpacing = resources.getDimensionPixelSize(R.dimen.spacing_large)

        genreAdapter = GenreAdapter { viewModel.onGenreSelected(it) }
        binding.genreRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = genreAdapter
        }

        filmAdapter = FilmAdapter {
            showErrorSnackbar(it.localizedName ?: "")
        }

        binding.moviesRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = filmAdapter
            addItemDecoration(GridItemDecoration(spacing, edgeSpacing))
        }

        binding.topAppBar.title = getString(R.string.film_header_text)
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.uiState.collect { state ->
                        binding.progressBar.isVisible = state.isLoading

                        genreAdapter.submitList(state.genres)
                        binding.genreHeader.isVisible = state.genres.isNotEmpty()

                        filmAdapter.submitList(state.films)
                        binding.filmHeader.isVisible = state.films.isNotEmpty()
                    }
                }
                launch {
                    viewModel.events.collect { event ->
                        when (event) {
                            is MainFragmentEvent.ShowError -> showErrorSnackbar(getString(event.resId))
                        }
                    }
                }
            }
        }
    }

    private fun showErrorSnackbar(message: String) {
        val snackbar = Snackbar.make(binding.root, message, Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.snackbar_action_text)) { viewModel.retry() }

        snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            ?.setTextAppearance(R.style.CustomSnackbarText)
        snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_action)
            ?.setTextAppearance(R.style.CustomSnackbarActionText)

        snackbar.show()
    }
}
