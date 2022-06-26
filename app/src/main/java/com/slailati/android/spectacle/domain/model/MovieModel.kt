package com.slailati.android.spectacle.domain.model

data class MovieModel(
    val remoteId: Int = -1,
    val localId: Int = -1,
    val genreIds: List<Int>,
    val title: String,
    val voteAverage: Float,
    val posterPath: String
)

