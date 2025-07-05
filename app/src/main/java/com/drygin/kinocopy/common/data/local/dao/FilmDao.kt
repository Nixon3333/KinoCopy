package com.drygin.kinocopy.common.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.drygin.kinocopy.common.data.local.entity.FilmEntity
import com.drygin.kinocopy.common.data.local.entity.FilmGenreCrossRef
import com.drygin.kinocopy.common.data.local.relation.FilmWithGenres
import kotlinx.coroutines.flow.Flow

/**
 * Created by Drygin Nikita on 03.07.2025.
 */
@Dao
interface FilmDao {
    @Transaction
    @Query("SELECT * FROM films")
    fun filmsWithGenres(): Flow<List<FilmWithGenres>>
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertAll(films: List<FilmEntity>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrossRefs(refs: List<FilmGenreCrossRef>)

    @Query("DELETE FROM films")
    suspend fun deleteAll()
}