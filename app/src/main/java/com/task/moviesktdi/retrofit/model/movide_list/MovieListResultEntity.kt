package com.task.moviesktdi.retrofit.model.movide_list

import com.google.gson.annotations.SerializedName
import com.task.moviesktdi.model.list.MovieListItem

data class MovieListResultEntity(
    @SerializedName("results")
    var results: List<MovieListItem>
)
