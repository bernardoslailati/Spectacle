package com.slailati.android.spectacle.data.local.datasource

import com.slailati.android.spectacle.data.local.database.MainDatabase
import com.slailati.android.spectacle.domain.mapper.toDomainMusicList
import com.slailati.android.spectacle.domain.mapper.toEntity
import com.slailati.android.spectacle.domain.model.MusicModel

class MyMusicsPlaylistDataSourceImpl(
    private val mainDatabase: MainDatabase
): MyMusicsPlaylistDataSource {

    override suspend fun insert(music: MusicModel): Long =
        mainDatabase.myMusicsPlaylistDao.insert(music.toEntity())

    override suspend fun delete(music: MusicModel) {
        mainDatabase.myMusicsPlaylistDao.delete(music.toEntity())
    }

    override suspend fun deleteById(musicId: Int) {
        mainDatabase.myMusicsPlaylistDao.deleteById(musicId)
    }

    override suspend fun getAll(): List<MusicModel> =
        mainDatabase.myMusicsPlaylistDao.getAll().toDomainMusicList()

    override suspend fun getAllByUserId(userId: String): List<MusicModel> =
        mainDatabase.myMusicsPlaylistDao.getAllByUserId(userId).toDomainMusicList()

}