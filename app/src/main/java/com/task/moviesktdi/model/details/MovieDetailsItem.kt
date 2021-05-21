package com.task.moviesktdi.model.details

data class MovieDetailsItem(
    var adult: Boolean,
    var backdrop_path: String,
    var id: Int,
    var imdb_id: String,
    var original_title: String,
    var overview: String,
    var poster_path: String,
    var release_date: String,
    var tagline: String,
    var vote_average: Double
)
