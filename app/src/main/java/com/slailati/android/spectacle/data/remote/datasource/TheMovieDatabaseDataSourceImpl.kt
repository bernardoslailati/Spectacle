package com.slailati.android.spectacle.data.remote.datasource

import com.slailati.android.spectacle.data.mapper.toDomainMusicList
import com.slailati.android.spectacle.domain.model.MovieModel
import com.slailati.android.spectacle.data.remote.service.TheMovieDatabaseService

class TheMovieDatabaseDataSourceImpl(
    private val theMovieDatabaseService: TheMovieDatabaseService
): TheMovieDatabaseDataSource {

    override suspend fun getMovies(page: Int): List<MovieModel> =
        theMovieDatabaseService.getPopularMovies(page = page).toDomainMusicList()

}