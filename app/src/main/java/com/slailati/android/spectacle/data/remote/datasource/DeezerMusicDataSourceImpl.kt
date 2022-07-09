package com.slailati.android.spectacle.data.remote.datasource

import com.slailati.android.spectacle.data.mapper.toDomainMusicList
import com.slailati.android.spectacle.domain.model.MusicModel
import com.slailati.android.spectacle.data.remote.service.DeezerService

class DeezerMusicDataSourceImpl(
    private val deezerService: DeezerService
): DeezerMusicDataSource {

    override suspend fun getMusics(): List<MusicModel> =
        deezerService.getMusics().toDomainMusicList()

}