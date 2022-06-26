package com.slailati.android.spectacle.domain.mapper

import com.slailati.android.spectacle.domain.database.MusicEntity
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

fun List<MusicEntity>.toDomainList(): List<MusicModel> = this.map { it.toDomain() }

fun MusicModel.toEntity(): MusicEntity {
    return MusicEntity(
        title = this.title,
        artistName = this.artistName,
        albumCoverUrl = this.albumCoverUrl
    )
}