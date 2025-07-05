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
    @param:StringRes val resId: Int? = null
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
            _uiState.update { it.copy(isLoading = true) }
            val result = repository.refreshFilms()
            when (result) {
                is Result.Failure -> {
                    _uiState.update { it.copy(resId = R.string.error_network_connection) }
                }
                is Result.Success -> {
                    _uiState.update { it.copy(resId = null) }
                }
            }
            _uiState.update { it.copy(isLoading = false) }
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
        val filtered = selectedGenre?.let { selected ->
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
        selectedGenre = if (selectedGenre == genre) null else genre
        rebuildUiState()
    }
}
