package com.task.moviesktdi.retrofit

import com.task.moviesktdi.retrofit.model.movide_list.MovieListResultEntity
import com.task.moviesktdi.retrofit.model.movie_cast.MovieCastListEntity
import com.task.moviesktdi.retrofit.model.movie_details.MovieDetailsEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("4/list/{list_id}")
    suspend fun retrieveMovieList(
        @Path("list_id") list_id: Int,
        @Query("page") page: Int,
        @Query("api_key") api_key: String
    ): MovieListResultEntity

    @GET("3/movie/{movie_id}")
    suspend fun retrieveMovieDetails(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): MovieDetailsEntity

    @GET("3/movie/{movie_id}/credits")
    suspend fun retrieveMovieCast(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): MovieCastListEntity
}