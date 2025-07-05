package com.drygin.kinocopy.screens.details.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drygin.kinocopy.common.data.mapper.toDomain
import com.drygin.kinocopy.common.domain.model.Film
import com.drygin.kinocopy.common.domain.repository.FilmRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Created by Drygin Nikita on 05.07.2025.
 */
class DetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val filmRepository: FilmRepository
) : ViewModel() {

    private val _film = MutableStateFlow<Film?>(null)
    val film: StateFlow<Film?> = _film

    init {
        val filmId = checkNotNull(savedStateHandle.get<Int>("filmId"))
        loadFilm(filmId)
    }

    private fun loadFilm(filmId: Int) {
        viewModelScope.launch {
            val film = filmRepository.getFilm(filmId)?.toDomain()
            _film.value = film
        }
    }
}
