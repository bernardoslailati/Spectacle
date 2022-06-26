package com.slailati.android.spectacle.domain.model

data class DeezerMusicsResultApiModel (
    val data: List<MusicApiModel>? = null
)

data class MusicApiModel(
    val title: String? = null,
    val artist: ArtistApiModel? = null,
    val album: AlbumApiModel? = null
)

data class ArtistApiModel (
    val name: String? = null
)

data class AlbumApiModel (
    val cover: String? = null
)

