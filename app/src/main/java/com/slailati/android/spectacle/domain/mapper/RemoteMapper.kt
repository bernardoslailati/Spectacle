package com.slailati.android.spectacle.domain.mapper

import com.slailati.android.spectacle.domain.model.*

fun MusicApiModel.toDomain(): MusicModel =
    MusicModel(
        title = this.title ?: "",
        artistName = this.artist?.name ?: "",
        albumCoverUrl = this.album?.cover ?: ""
    )

fun DeezerMusicsResultApiModel.toDomainMusicList(): List<MusicModel> =
    this.data?.map { it.toDomain() } ?: emptyList()

fun TheMovieDatabaseMovieApiModel.toDomain(): MovieModel =
    MovieModel(
        remoteId = this.id ?: -1,
        title = this.title ?: "",
        voteAverage = this.voteAverage ?: -1f,
        posterPath = this.posterPath ?: "",
        genreIds = this.genreIds ?: emptyList()
    )

fun TheMovieDatabaseMovieResultApiModel.toDomainMusicList(): List<MovieModel> =
    this.results?.map { it.toDomain() } ?: emptyList()