package com.drygin.kinocopy.common.domain.repository

import com.drygin.kinocopy.common.data.local.relation.FilmWithGenres
import com.drygin.kinocopy.common.domain.model.Film
import com.drygin.kinocopy.common.utils.Result
import kotlinx.coroutines.flow.Flow

/**
* Created by Drygin Nikita on 02.07.2025.
*/
interface FilmRepository {
    suspend fun refreshFilms(): Result<List<Film>>
    fun getFilms(): Flow<List<Film>>
    suspend fun getFilm(filmId: Int) : FilmWithGenres?
}