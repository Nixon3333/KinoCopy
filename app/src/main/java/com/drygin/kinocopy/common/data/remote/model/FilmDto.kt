package com.drygin.kinocopy.common.data.remote.model

import com.squareup.moshi.Json

/**
 * Created by Drygin Nikita on 02.07.2025.
 */
data class FilmDto(
    val id: Int,
    @param:Json(name = "localized_name") val localizedName: String? = null,
    val name: String,
    val year: Int,
    val rating: Double?,
    @param:Json(name = "image_url") val imageUrl: String? = null,
    val description: String? = null,
    val genres: List<String>
)