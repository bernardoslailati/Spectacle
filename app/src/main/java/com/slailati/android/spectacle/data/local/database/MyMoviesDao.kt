package com.slailati.android.spectacle.data.local.database

import androidx.room.*
import com.slailati.android.spectacle.data.local.model.MovieEntity

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