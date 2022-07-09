package com.slailati.android.spectacle.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_musics_playlist")
data class MusicEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var userId: String = "",
    var title: String,
    var artistName: String,
    var albumCoverUrl: String
)
