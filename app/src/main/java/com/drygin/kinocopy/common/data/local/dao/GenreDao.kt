package com.drygin.kinocopy.common.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.drygin.kinocopy.common.data.local.entity.GenreEntity

/**
 * Created by Drygin Nikita on 04.07.2025.
 */
@Dao
interface GenreDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGenres(genres: List<GenreEntity>)
    @Query("SELECT * FROM genres")
    suspend fun genres(): List<GenreEntity>
}
