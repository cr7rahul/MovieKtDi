package com.task.moviesktdi.room

import androidx.room.*

@Dao
interface MoviesDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun postInfo(entity: MovieListItemCacheEntity): Long

    @Query("SELECT * FROM movies")
    suspend fun retrieveList(): List<MovieListItemCacheEntity>

    @Query("SELECT * FROM movies WHERE id =:id")
    suspend fun retrieveDetails(id: Int): List<MovieListItemCacheEntity>

    @Query("DELETE FROM movies")
    suspend fun deleteAll()
}