package com.task.moviesktdi.repository

import com.task.moviesktdi.model.details.MovieDetailsItem
import com.task.moviesktdi.model.list.MovieListItem
import com.task.moviesktdi.retrofit.ApiInterface
import com.task.moviesktdi.retrofit.mapper.MovieCastNetworkMapper
import com.task.moviesktdi.retrofit.mapper.MovieDetailsNetworkMapper
import com.task.moviesktdi.retrofit.mapper.NetworkMapper
import com.task.moviesktdi.retrofit.model.movie_cast.MovieCastItemsEntity
import com.task.moviesktdi.room.CacheMapper
import com.task.moviesktdi.room.MoviesDAO
import com.task.moviesktdi.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class MovieRepository
@Inject
constructor(
    private val movieDao: MoviesDAO,
    private val apiInterface: ApiInterface,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper,
    private var movieDetailsNetworkMapper: MovieDetailsNetworkMapper,
    private var movieCastNetworkMapper: MovieCastNetworkMapper
) {
    suspend fun retrieveMovieList(
        list_id: Int,
        page: Int,
        apiKey: String
    ): Flow<DataState<List<MovieListItem>>> = flow {
        emit(DataState.Loading)
        try {
            val movieList = apiInterface.retrieveMovieList(list_id, page, apiKey)
            val movies = networkMapper.mapFromEntity(movieList)
            movieDao.deleteAll()
            for (movie in movies.results) {
                val cacheEntity = MovieListItem(
                    movie.adult,
                    movie.backdrop_path,
                    movie.genre_ids,
                    movie.id,
                    movie.media_type,
                    movie.original_language,
                    movie.original_title,
                    movie.overview,
                    movie.popularity,
                    movie.poster_path,
                    movie.release_date,
                    movie.title,
                    movie.video,
                    movie.vote_average,
                    movie.vote_count
                )
                movieDao.postInfo(cacheMapper.mapFromEntity(cacheEntity))
            }
            val cachedMovie = movieDao.retrieveList()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedMovie)))
        } catch (ex: Exception) {
            emit(DataState.Error(ex))
        }
    }

    suspend fun retrieveMovieDetails(
        movie_id: Int,
        api_key: String,
        language: String
    ): Flow<DataState<MovieDetailsItem>> = flow {
        emit(DataState.Loading)
        try {
            val movieDetails = apiInterface.retrieveMovieDetails(movie_id, api_key, language)
            emit(DataState.Success(movieDetailsNetworkMapper.mapFromEntity(movieDetails)))
        } catch (ex: Exception) {
            emit(DataState.Error(ex))
        }
    }


}