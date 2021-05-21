package com.task.moviesktdi.di

import com.task.moviesktdi.repository.MovieRepository
import com.task.moviesktdi.retrofit.ApiInterface
import com.task.moviesktdi.retrofit.mapper.MovieDetailsNetworkMapper
import com.task.moviesktdi.retrofit.mapper.NetworkMapper
import com.task.moviesktdi.room.CacheMapper
import com.task.moviesktdi.room.MoviesDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @MovieList
    @Singleton
    @Provides
    fun provideMovieRepository(
        movieDao: MoviesDAO,
        apiInterface: ApiInterface,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper,
        movieDetailsNetworkMapper: MovieDetailsNetworkMapper
    ): MovieRepository {
        return MovieRepository(
            movieDao,
            apiInterface,
            cacheMapper,
            networkMapper,
            movieDetailsNetworkMapper
        )
    }

    @MovieDetails
    @Singleton
    @Provides
    fun provideMovieDetails(
        movieDao: MoviesDAO,
        apiInterface: ApiInterface,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper,
        movieDetailsNetworkMapper: MovieDetailsNetworkMapper
    ): MovieRepository {
        return MovieRepository(
            movieDao,
            apiInterface,
            cacheMapper,
            networkMapper,
            movieDetailsNetworkMapper
        )
    }

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class MovieList

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class MovieDetails
}