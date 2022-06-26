package com.slailati.android.spectacle.data.datasource

import com.slailati.android.spectacle.domain.database.MainDatabase
import com.slailati.android.spectacle.domain.mapper.toDomainList
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
        mainDatabase.myMusicsPlaylistDao.getAll().toDomainList()

    override suspend fun getAllByUserId(userId: String): List<MusicModel> =
        mainDatabase.myMusicsPlaylistDao.getAllByUserId(userId).toDomainList()

}