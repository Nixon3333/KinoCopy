package com.drygin.kinocopy.common.data.repository

import com.drygin.kinocopy.common.data.local.dao.FilmDao
import com.drygin.kinocopy.common.data.local.dao.GenreDao
import com.drygin.kinocopy.common.data.local.entity.FilmGenreCrossRef
import com.drygin.kinocopy.common.data.local.entity.GenreEntity
import com.drygin.kinocopy.common.data.mapper.toDomain
import com.drygin.kinocopy.common.data.mapper.toEntity
import com.drygin.kinocopy.common.data.remote.api.FilmApi
import com.drygin.kinocopy.common.utils.Result
import com.drygin.kinocopy.features.home.domain.model.Film
import com.drygin.kinocopy.features.home.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by Drygin Nikita on 02.07.2025.
 */
class FilmRepositoryImpl(
    private val api: FilmApi,
    private val filmDao: FilmDao,
    private val genreDao: GenreDao
) : FilmRepository {

    override fun getFilms(): Flow<List<Film>> =
        filmDao.filmsWithGenres().map { entities -> entities.map { it.toDomain() } }

    override suspend fun refreshFilms(): Result<List<Film>> = try {
        val response = api.getFilms().filmDtoList
        val filmEntities = response.map { it.toEntity() }
        val genreEntities = response.flatMap { it.genres }.distinct().map { GenreEntity(it) }
        val crossRefs = response.flatMap { film ->
            film.genres.map { genre -> FilmGenreCrossRef(film.id, genre) }
        }
        filmDao.deleteAll()

        filmDao.insertAll(filmEntities)
        genreDao.insertGenres(genreEntities)
        filmDao.insertCrossRefs(crossRefs)

        val domainModels = response.map { it.toDomain() }
        Result.Success(domainModels)
    } catch (e: Exception) {
        Result.Failure(e)
    }
}