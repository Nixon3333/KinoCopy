package com.drygin.kinocopy.common.data.local.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.drygin.kinocopy.common.data.local.entity.FilmEntity
import com.drygin.kinocopy.common.data.local.entity.FilmGenreCrossRef
import com.drygin.kinocopy.common.data.local.entity.GenreEntity

/**
 * Created by Drygin Nikita on 04.07.2025.
 */
data class FilmWithGenres(
    @Embedded val film: FilmEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "name",
        associateBy = Junction(
            FilmGenreCrossRef::class,
            parentColumn = "film_id",
            entityColumn = "genre_name"
        )
    )
    val genres: List<GenreEntity>
)