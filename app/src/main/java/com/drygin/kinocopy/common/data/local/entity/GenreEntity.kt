package com.drygin.kinocopy.common.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Drygin Nikita on 04.07.2025.
 */
@Entity(tableName = "genres")
data class GenreEntity(
    @PrimaryKey val name: String
)