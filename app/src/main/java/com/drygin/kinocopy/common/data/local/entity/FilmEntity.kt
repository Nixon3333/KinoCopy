package com.drygin.kinocopy.common.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Drygin Nikita on 03.07.2025.
 */
@Entity(tableName = "films")
data class FilmEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "localized_name") val localizedName: String? = null,
    val name: String,
    val year: Int,
    val rating: Double?,
    @ColumnInfo(name = "image_url") val imageUrl: String? = null,
    val description: String? = null
)
