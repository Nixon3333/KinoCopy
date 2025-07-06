package com.drygin.kinocopy.screens.home.presentation.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drygin.kinocopy.R
import com.drygin.kinocopy.common.data.repository.FilmRepositoryImpl
import com.drygin.kinocopy.common.domain.model.Film
import com.drygin.kinocopy.common.domain.model.Genre
import com.drygin.kinocopy.common.utils.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Created by Drygin Nikita on 02.07.2025.
 */
data class MainFragmentUiState(
    val isLoading: Boolean = false,
    val genres: List<Genre> = emptyList(),
    val films: List<Film> = emptyList(),
    val selectedGenre: String? = null,
    @param:StringRes val errorMessageResId: Int? = null
)

class MainFragmentViewModel(
    private val repository: FilmRepositoryImpl
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainFragmentUiState())
    val uiState: StateFlow<MainFragmentUiState> = _uiState.asStateFlow()

    private var films = emptyList<Film>()
    private var genres = emptyList<String>()
    private var selectedGenre: String? = null

    init {
        observeFilms()
        refresh()
    }

    fun retry() {
        refresh()
    }

    private fun refresh() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessageResId = null) }
            val result = repository.refreshFilms()

            val errorMessageResId = when (result) {
                is Result.Failure -> R.string.error_network_connection
                is Result.Success -> null
            }

            _uiState.update { it.copy(isLoading = false, errorMessageResId = errorMessageResId) }
        }
    }

    private fun observeFilms() {
        viewModelScope.launch {
            repository.getFilms().collect { filmList ->
                films = filmList
                genres = filmList
                    .flatMap { it.genres }
                    .filter { it.isNotBlank() }
                    .distinct()
                    .sorted()

                rebuildUiState()
            }
        }
    }

    private fun rebuildUiState() {
        val filtered = uiState.value.selectedGenre?.let { selected ->
            films.filter { selected in it.genres }
        } ?: films

        val genreItems = genres.map { genre ->
            Genre(name = genre, isSelected = genre == selectedGenre)
        }

        _uiState.update {
            it.copy(
                genres = genreItems,
                films = filtered
            )
        }
    }

    fun onGenreSelected(genre: String) {
        selectedGenre = if (uiState.value.selectedGenre == genre) null else genre
        _uiState.update { it.copy(selectedGenre = selectedGenre) }
        rebuildUiState()
    }
}
