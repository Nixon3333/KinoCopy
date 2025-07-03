package com.drygin.kinocopy.features.home.presentation

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drygin.kinocopy.R
import com.drygin.kinocopy.features.home.domain.model.Film
import com.drygin.kinocopy.features.home.data.repository.FilmRepositoryImpl
import com.drygin.kinocopy.common.utils.Result
import com.drygin.kinocopy.features.home.presentation.model.GenreItem
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Created by Drygin Nikita on 02.07.2025.
 */
data class MainFragmentUiState(
    val isLoading: Boolean = false,
    val genres: List<GenreItem> = emptyList(),
    val films: List<Film> = emptyList()
)

sealed class MainFragmentEvent {
    data class ShowError(@param:StringRes val resId: Int) : MainFragmentEvent()
}

class MainFragmentViewModel(
    private val repository: FilmRepositoryImpl
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainFragmentUiState())
    val uiState: StateFlow<MainFragmentUiState> = _uiState.asStateFlow()

    private val _eventChannel = Channel<MainFragmentEvent>(Channel.BUFFERED)
    val events = _eventChannel.receiveAsFlow()

    private var allFilms = emptyList<Film>()
    private var genrePairs = emptyList<Pair<String, String>>()
    private var selectedGenre: String? = null

    init {
        loadData()
    }

    fun retry() {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            when (val result = repository.getFilms()) {
                is Result.Success -> {
                    allFilms = result.data
                    genrePairs = allFilms
                        .flatMap { it.genres }
                        .filter { it.isNotBlank() }
                        .distinct()
                        .map { it.replaceFirstChar(Char::uppercaseChar) to it }
                        .sortedBy { it.first }
                    selectedGenre = null
                    rebuildUiState()
                }

                is Result.Failure -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            genres = emptyList(),
                            films = emptyList()
                        )
                    }
                    _eventChannel.send(
                        MainFragmentEvent.ShowError(R.string.error_network_connection)
                    )
                }
            }

            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun onGenreSelected(genre: String) {
        selectedGenre = if (selectedGenre == genre) null else genre
        rebuildUiState()
    }

    private fun rebuildUiState() {
        val displayedFilms = selectedGenre?.let { sel ->
            val original = genrePairs.firstOrNull { it.first == sel }?.second
            allFilms.filter { original != null && original in it.genres }
        } ?: allFilms

        val genreItems = genrePairs.map { (displayed, _) ->
            GenreItem(displayed, displayed == selectedGenre)
        }

        _uiState.update {
            it.copy(
                genres = genreItems,
                films = displayedFilms
            )
        }
    }
}
