package com.task.moviesktdi.retrofit.model.movie_cast

import com.google.gson.annotations.SerializedName
import com.task.moviesktdi.model.cast.MovieCastItems

data class MovieCastListEntity(
    @SerializedName("cast")
    val cast: List<MovieCastItems>
)
