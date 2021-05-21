package com.task.moviesktdi.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieListItemCacheEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MoviesDAO

    companion object {
        val db_name = "movie_db"
    }
}