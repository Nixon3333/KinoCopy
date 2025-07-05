package com.drygin.kinocopy.features.home.domain.repository

import com.drygin.kinocopy.common.utils.Result
import com.drygin.kinocopy.features.home.domain.model.Film
import kotlinx.coroutines.flow.Flow

/**
* Created by Drygin Nikita on 02.07.2025.
*/
interface FilmRepository {
    suspend fun refreshFilms(): Result<List<Film>>
    fun getFilms(): Flow<List<Film>>
}