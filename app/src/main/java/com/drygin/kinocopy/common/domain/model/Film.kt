package com.drygin.kinocopy.common.domain.model

/**
* Created by Drygin Nikita on 03.07.2025.
*/
data class Film(
    val id: Int,
    val localizedName: String? = null,
    val name: String,
    val year: Int,
    val rating: Double?,
    val imageUrl: String? = null,
    val description: String? = null,
    val genres: List<String>
)