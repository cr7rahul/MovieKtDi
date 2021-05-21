package com.task.moviesktdi.retrofit.mapper

import com.task.moviesktdi.model.details.MovieDetailsItem
import com.task.moviesktdi.retrofit.model.movie_details.MovieDetailsEntity
import com.task.moviesktdi.util.EntityMapper
import javax.inject.Inject

class MovieDetailsNetworkMapper
@Inject
constructor() : EntityMapper<MovieDetailsEntity, MovieDetailsItem> {
    override fun mapToEntity(domainModel: MovieDetailsItem): MovieDetailsEntity {
        return MovieDetailsEntity(
            adult = domainModel.adult,
            backdrop_path = domainModel.backdrop_path,
            id = domainModel.id,
            imdb_id = domainModel.imdb_id,
            original_title = domainModel.original_title,
            overview = domainModel.overview,
            poster_path = domainModel.poster_path,
            release_date = domainModel.release_date,
            tagline = domainModel.tagline,
            vote_average = domainModel.vote_average,
        )
    }

    override fun mapFromEntity(entity: MovieDetailsEntity): MovieDetailsItem {
        return MovieDetailsItem(
            adult = entity.adult,
            backdrop_path = entity.backdrop_path,
            id = entity.id,
            imdb_id = entity.imdb_id,
            original_title = entity.original_title,
            overview = entity.overview,
            poster_path = entity.poster_path,
            release_date = entity.release_date,
            tagline = entity.tagline,
            vote_average = entity.vote_average,
        )
    }

    fun mapFromEntityList(entities: List<MovieDetailsEntity>): List<MovieDetailsItem> {
        return entities.map { mapFromEntity(it) }
    }

}