package com.drygin.kinocopy.common.data.mapper

import com.drygin.kinocopy.common.data.local.entity.FilmEntity
import com.drygin.kinocopy.common.data.local.relation.FilmWithGenres
import com.drygin.kinocopy.common.data.remote.model.FilmDto
import com.drygin.kinocopy.features.home.domain.model.Film

/**
 * Created by Drygin Nikita on 03.07.2025.
 */
fun FilmDto.toDomain() = Film(
    this.id,
    this.localizedName,
    this.name,
    this.year,
    this.rating,
    this.imageUrl,
    this.description,
    this.genres
)

fun FilmWithGenres.toDomain() = Film(
    id = film.id,
    localizedName = film.localizedName,
    name = film.name,
    year = film.year,
    rating = film.rating,
    imageUrl = film.imageUrl,
    description = film.description,
    genres = genres.map { it.name.replaceFirstChar(Char::uppercaseChar) }
)

fun FilmDto.toEntity() = FilmEntity(
    this.id,
    this.localizedName,
    this.name,
    this.year,
    this.rating,
    this.imageUrl,
    this.description
)