package com.task.moviesktdi.retrofit.mapper

import com.task.moviesktdi.model.list.MovieListResult
import com.task.moviesktdi.retrofit.model.movide_list.MovieListResultEntity
import com.task.moviesktdi.util.EntityMapper
import javax.inject.Inject

class NetworkMapper
@Inject
constructor() : EntityMapper<MovieListResultEntity, MovieListResult> {

    override fun mapToEntity(domainModel: MovieListResult): MovieListResultEntity {
        return MovieListResultEntity(
            results = domainModel.results
        )
    }

    override fun mapFromEntity(entity: MovieListResultEntity): MovieListResult {
        return MovieListResult(
            results = entity.results
        )
    }
}