package com.task.moviesktdi.room

import com.task.moviesktdi.model.list.MovieListItem
import com.task.moviesktdi.util.EntityMapper
import javax.inject.Inject

class CacheMapper
@Inject
constructor() : EntityMapper<MovieListItem, MovieListItemCacheEntity> {
    override fun mapToEntity(domainModel: MovieListItemCacheEntity): MovieListItem {
        return MovieListItem(
            adult = domainModel.adult,
            backdrop_path = domainModel.backdrop_path,
            genre_ids = listOf(1),
            id = domainModel.id,
            media_type = domainModel.media_type,
            original_language = domainModel.original_language,
            original_title = domainModel.original_title,
            overview = domainModel.overview,
            popularity = domainModel.popularity,
            poster_path = domainModel.poster_path,
            release_date = domainModel.release_date,
            title = domainModel.title,
            video = domainModel.video,
            vote_average = domainModel.vote_average,
            vote_count = domainModel.vote_count
        )
    }

    override fun mapFromEntity(entity: MovieListItem): MovieListItemCacheEntity {
        return MovieListItemCacheEntity(
            adult = entity.adult,
            backdrop_path = entity.backdrop_path,
            id = entity.id,
            media_type = entity.media_type,
            original_language = entity.original_language,
            original_title = entity.original_title,
            overview = entity.overview,
            popularity = entity.popularity,
            poster_path = entity.poster_path,
            release_date = entity.release_date,
            title = entity.title,
            video = entity.video,
            vote_average = entity.vote_average,
            vote_count = entity.vote_count
        )
    }

    fun mapFromEntityList(entities: List<MovieListItemCacheEntity>): List<MovieListItem> {
        return entities.map { mapToEntity(it) }
    }

}