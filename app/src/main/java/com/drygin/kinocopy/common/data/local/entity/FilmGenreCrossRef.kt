package com.drygin.kinocopy.common.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

/**
 * Created by Drygin Nikita on 04.07.2025.
 */
@Entity(
    tableName = "film_genre_cross_ref",
    primaryKeys = ["film_id", "genre_name"],
    foreignKeys = [
        ForeignKey(
            entity = FilmEntity::class,
            parentColumns = ["id"],
            childColumns = ["film_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = GenreEntity::class,
            parentColumns = ["name"],
            childColumns = ["genre_name"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["film_id"]),
        Index(value = ["genre_name"])
    ]
)

data class FilmGenreCrossRef(
    @ColumnInfo(name = "film_id")
    val filmId: Int,

    @ColumnInfo(name = "genre_name")
    val genreName: String
)
