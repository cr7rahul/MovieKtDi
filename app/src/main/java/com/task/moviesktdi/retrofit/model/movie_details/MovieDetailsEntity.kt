package com.task.moviesktdi.retrofit.model.movie_details

import com.google.gson.annotations.SerializedName

data class MovieDetailsEntity(
    @SerializedName("adult")
    var adult: Boolean,
    @SerializedName("backdrop_path")
    var backdrop_path: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("imdb_id")
    var imdb_id: String,
    @SerializedName("original_title")
    var original_title: String,
    @SerializedName("overview")
    var overview: String,
    @SerializedName("poster_path")
    var poster_path: String,
    @SerializedName("release_date")
    var release_date: String,
    @SerializedName("tagline")
    var tagline: String,
    @SerializedName("vote_average")
    var vote_average: Double
)
