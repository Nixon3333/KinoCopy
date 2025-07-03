package com.drygin.kinocopy.features.home.domain.repository

import com.drygin.kinocopy.features.home.domain.model.Film
import com.drygin.kinocopy.common.utils.Result

/**
* Created by Drygin Nikita on 02.07.2025.
*/
interface FilmRepository {
    suspend fun getFilms(): Result<List<Film>>
}