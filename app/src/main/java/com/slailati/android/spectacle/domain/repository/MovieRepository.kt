package com.slailati.android.spectacle.domain.repository

import com.slailati.android.spectacle.domain.model.MovieModel

interface MovieRepository {

    suspend fun getMovieByGenreId(page: Int, genreId: Int): List<MovieModel>

    suspend fun insertMovieToMyList(genreId: Int, movie: MovieModel): Boolean

    suspend fun deleteMovieFromMyList(movieId: Int)

    suspend fun getMyMovies(): List<MovieModel>

    suspend fun getMyMoviesByGenre(genreId: Int): List<MovieModel>

}