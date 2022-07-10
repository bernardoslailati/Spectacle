package com.slailati.android.spectacle.domain.mapper

import com.slailati.android.spectacle.data.local.model.MovieEntity
import com.slailati.android.spectacle.data.local.model.MusicEntity
import com.slailati.android.spectacle.domain.model.MovieModel
import com.slailati.android.spectacle.domain.model.MusicModel

fun MusicEntity.toDomain(): MusicModel {
    return MusicModel(
        localId = this.id,
        userId = this.userId,
        title = this.title,
        artistName = this.artistName,
        albumCoverUrl = this.albumCoverUrl
    )
}

fun List<MusicEntity>.toDomainMusicList(): List<MusicModel> = this.map { it.toDomain() }

fun MusicModel.toEntity(): MusicEntity {
    return MusicEntity(
        title = this.title,
        artistName = this.artistName,
        albumCoverUrl = this.albumCoverUrl
    )
}

fun MovieEntity.toDomain(): MovieModel {
    return MovieModel(
        remoteId = this.id,
        localId = this.id,
        title = this.title,
        genreIds = listOf(this.genreId),
        posterPath = this.posterPath,
        voteAverage = this.voteAverage
    )
}

fun List<MovieEntity>.toDomainMovieList(): List<MovieModel> = this.map { it.toDomain() }

fun MovieModel.toEntity(): MovieEntity {
    return MovieEntity(
        id = this.localId,
        remoteId = this.remoteId,
        genreId = if (this.genreIds.isEmpty()) -1 else this.genreIds.first(),
        title = this.title,
        posterPath = this.posterPath,
        voteAverage = this.voteAverage
    )
}

fun MovieModel.toEntity(genreId: Int): MovieEntity {
    return MovieEntity(
        remoteId = this.remoteId,
        genreId = genreId,
        title = this.title,
        posterPath = this.posterPath,
        voteAverage = this.voteAverage
    )
}