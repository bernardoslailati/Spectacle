package com.slailati.android.spectacle.domain.repository

import com.slailati.android.spectacle.data.local.datasource.MyMoviesDataSource
import com.slailati.android.spectacle.data.remote.datasource.TheMovieDatabaseDataSource
import com.slailati.android.spectacle.domain.model.MovieModel

class MovieRepositoryImpl(
    private val theMovieDatabaseDataSource: TheMovieDatabaseDataSource,
    private val myMoviesDataSource: MyMoviesDataSource
): MovieRepository {

    override suspend fun getMovieByGenreId(page: Int, genreId: Int): List<MovieModel> =
        theMovieDatabaseDataSource.getMovies(page).filter { it.genreIds.contains(genreId) }

    override suspend fun insertMovieToMyList(genreId: Int, movie: MovieModel): Boolean {
        val isInserted = myMoviesDataSource.insert(genreId, movie)
        return isInserted > -1
    }

    override suspend fun deleteMovieFromMyList(movieId: Int) =
        myMoviesDataSource.deleteById(movieId)

    override suspend fun getMyMovies(): List<MovieModel> =
        myMoviesDataSource.getAll()

    override suspend fun getMyMoviesByGenre(genreId: Int): List<MovieModel> =
        myMoviesDataSource.getAllByGenreId(genreId)

}