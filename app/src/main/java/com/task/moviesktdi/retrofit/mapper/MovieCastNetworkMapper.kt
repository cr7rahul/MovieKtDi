package com.task.moviesktdi.retrofit.mapper

import com.task.moviesktdi.model.cast.MovieCastList
import com.task.moviesktdi.retrofit.model.movie_cast.MovieCastListEntity
import com.task.moviesktdi.util.EntityMapper
import javax.inject.Inject

class MovieCastNetworkMapper
@Inject
constructor() : EntityMapper<MovieCastListEntity, MovieCastList> {
    override fun mapToEntity(domainModel: MovieCastList): MovieCastListEntity {
        return MovieCastListEntity(
            cast = domainModel.cast
        )
    }

    override fun mapFromEntity(entity: MovieCastListEntity): MovieCastList {
        return MovieCastList(
            cast = entity.cast
        )
    }
}