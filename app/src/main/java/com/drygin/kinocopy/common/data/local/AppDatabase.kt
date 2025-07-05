package com.drygin.kinocopy.common.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.drygin.kinocopy.common.data.local.dao.FilmDao
import com.drygin.kinocopy.common.data.local.dao.GenreDao
import com.drygin.kinocopy.common.data.local.entity.FilmEntity
import com.drygin.kinocopy.common.data.local.entity.FilmGenreCrossRef
import com.drygin.kinocopy.common.data.local.entity.GenreEntity

/**
 * Created by Drygin Nikita on 03.07.2025.
 */
@Database(
    entities = [FilmEntity::class, GenreEntity::class, FilmGenreCrossRef::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmDao() : FilmDao
    abstract fun genreDao() : GenreDao
}