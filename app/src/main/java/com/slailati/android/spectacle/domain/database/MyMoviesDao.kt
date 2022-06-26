package com.slailati.android.spectacle.domain.database

import androidx.room.*

@Dao
interface MyMoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: MovieEntity): Long

    @Delete
    fun delete(movie: MovieEntity)

    @Query("DELETE FROM my_movies WHERE id = :movieId")
    fun deleteById(movieId: Int)

    @Query("SELECT * FROM my_movies")
    fun getAll(): List<MovieEntity>

    @Query("SELECT * FROM my_movies WHERE genreId = :genreId")
    fun getAllByGenreId(genreId: Int): List<MovieEntity>

}