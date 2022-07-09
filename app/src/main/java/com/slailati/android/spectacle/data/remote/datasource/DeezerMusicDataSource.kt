package com.slailati.android.spectacle.data.remote.datasource

import com.slailati.android.spectacle.domain.model.MusicModel

interface DeezerMusicDataSource {

    suspend fun getMusics() : List<MusicModel>

}