package com.slailati.android.spectacle.data.local.model

import com.google.gson.annotations.SerializedName

data class TheMovieDatabaseMovieResultApiModel (
    val page: Int? = null,
    val results: List<TheMovieDatabaseMovieApiModel>? = null
)

data class TheMovieDatabaseMovieApiModel (
    val id: Int? = null,
    val title: String? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("genre_ids") val genreIds: List<Int>? = null,
    @SerializedName("vote_average") val voteAverage: Float? = null
)