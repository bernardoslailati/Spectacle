package com.slailati.android.spectacle.data.local.datasource

import com.slailati.android.spectacle.domain.model.MusicModel

interface MyMusicsPlaylistDataSource {

    suspend fun insert(music: MusicModel): Long

    suspend fun delete(music: MusicModel)

    suspend fun deleteById(musicId: Int)

    suspend fun getAll(): List<MusicModel>

    suspend fun getAllByUserId(userId: String): List<MusicModel>

}