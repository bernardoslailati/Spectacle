package com.slailati.android.spectacle.data.remote.datasource

import com.slailati.android.spectacle.domain.model.MovieModel

interface TheMovieDatabaseDataSource {

    suspend fun getMovies(page: Int): List<MovieModel>

}