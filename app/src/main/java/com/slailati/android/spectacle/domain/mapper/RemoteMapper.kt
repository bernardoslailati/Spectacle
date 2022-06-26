package com.slailati.android.spectacle.domain.mapper

import com.slailati.android.spectacle.domain.model.DeezerMusicsResultApiModel
import com.slailati.android.spectacle.domain.model.MusicApiModel
import com.slailati.android.spectacle.domain.model.MusicModel

fun MusicApiModel.toDomain(): MusicModel =
    MusicModel(
        title = this.title ?: "",
        artistName = this.artist?.name ?: "",
        albumCoverUrl = this.album?.cover ?: ""
    )

fun DeezerMusicsResultApiModel.toDomainList(): List<MusicModel> =
    this.data?.map { it.toDomain() } ?: emptyList()

