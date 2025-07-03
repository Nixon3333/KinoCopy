package com.drygin.kinocopy.features.home.data.repository

import com.drygin.kinocopy.features.home.data.api.FilmApi
import com.drygin.kinocopy.features.home.domain.model.Film
import com.drygin.kinocopy.features.home.domain.repository.FilmRepository
import com.drygin.kinocopy.common.utils.Result

/**
 * Created by Drygin Nikita on 02.07.2025.
 */
class FilmRepositoryImpl(
    private val api: FilmApi
) : FilmRepository {

    override suspend fun getFilms(): Result<List<Film>> = try {
        val response = api.getFilms()
        Result.Success(response.films)
    } catch (e: Exception) {
        Result.Failure(e)
    }
}