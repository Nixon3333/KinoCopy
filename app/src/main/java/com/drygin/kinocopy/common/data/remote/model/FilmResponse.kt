package com.drygin.kinocopy.common.data.remote.model

import com.squareup.moshi.Json

/**
 * Created by Drygin Nikita on 02.07.2025.
 */
data class FilmResponse(
    @param:Json(name = "films")
    val filmDtoList: List<FilmDto>
)