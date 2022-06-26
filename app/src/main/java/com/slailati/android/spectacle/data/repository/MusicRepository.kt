package com.slailati.android.spectacle.data.repository

import com.slailati.android.spectacle.domain.model.MusicModel

interface MusicRepository {

    suspend fun getNewMusics(): List<MusicModel>

    suspend fun insertMusicToMyPlaylist(music: MusicModel): Boolean

    suspend fun deleteMusicFromMyPlaylist(musicId: Int)

    suspend fun getMyMusicsPlaylist(): List<MusicModel>

}