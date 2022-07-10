package com.slailati.android.spectacle.data.local.datasource

import com.slailati.android.spectacle.data.local.database.MainDatabase
import com.slailati.android.spectacle.domain.mapper.toDomainMovieList
import com.slailati.android.spectacle.domain.mapper.toEntity
import com.slailati.android.spectacle.domain.model.MovieModel

class MyMoviesDataSourceImpl(
    private val mainDatabase: MainDatabase
): MyMoviesDataSource {

    override suspend fun insert(genreId: Int, movie: MovieModel): Long =
        mainDatabase.myMoviesDao.insert(movie.toEntity(genreId))

    override suspend fun delete(movie: MovieModel) =
        mainDatabase.myMoviesDao.delete(movie.toEntity())

    override suspend fun deleteById(movieId: Int) =
        mainDatabase.myMoviesDao.deleteById(movieId)

    override suspend fun getAll(): List<MovieModel> =
        mainDatabase.myMoviesDao.getAll().toDomainMovieList()

    override suspend fun getAllByGenreId(genreId: Int): List<MovieModel> =
        mainDatabase.myMoviesDao.getAllByGenreId(genreId).toDomainMovieList()

}