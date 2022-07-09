package com.slailati.android.spectacle.data.local.datasource

import com.slailati.android.spectacle.domain.model.MovieModel

interface MyMoviesDataSource {

    suspend fun insert(genreId: Int, movie: MovieModel): Long

    suspend fun delete(movie: MovieModel)

    suspend fun deleteById(movieId: Int)

    suspend fun getAll(): List<MovieModel>

    suspend fun getAllByGenreId(genreId: Int): List<MovieModel>

}