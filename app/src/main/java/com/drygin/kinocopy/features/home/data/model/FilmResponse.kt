package com.drygin.kinocopy.features.home.data.model

import com.drygin.kinocopy.features.home.domain.model.Film

/**
 * Created by Drygin Nikita on 02.07.2025.
 */
data class FilmResponse(
    val films: List<Film>
)