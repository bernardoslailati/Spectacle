package com.slailati.android.spectacle.domain.model

data class MusicModel (
    val localId: Int = -1,
    val userId: String = "",
    val title: String,
    val artistName: String,
    val albumCoverUrl: String
)