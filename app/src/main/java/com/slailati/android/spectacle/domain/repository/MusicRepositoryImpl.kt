package com.slailati.android.spectacle.domain.repository

import com.slailati.android.spectacle.data.remote.datasource.DeezerMusicDataSource
import com.slailati.android.spectacle.data.local.datasource.MyMusicsPlaylistDataSource
import com.slailati.android.spectacle.domain.model.MusicModel

class MusicRepositoryImpl(
    private val deezerMusicDataSource: DeezerMusicDataSource,
    private val myMusicsPlaylistDataSource: MyMusicsPlaylistDataSource,
) : MusicRepository {

    override suspend fun getNewMusics(): List<MusicModel> =
        deezerMusicDataSource.getMusics()

    override suspend fun insertMusicToMyPlaylist(music: MusicModel): Boolean {
        val isInserted = myMusicsPlaylistDataSource.insert(music)
        return isInserted > -1
    }

    override suspend fun deleteMusicFromMyPlaylist(musicId: Int) =
        myMusicsPlaylistDataSource.deleteById(musicId)

    override suspend fun getMyMusicsPlaylist(): List<MusicModel> =
        myMusicsPlaylistDataSource.getAll()

}