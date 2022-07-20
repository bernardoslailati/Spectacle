package com.slailati.android.spectacle.data.remote.service

import com.slailati.android.spectacle.BuildConfig
import com.slailati.android.spectacle.data.local.model.TheMovieDatabaseMovieResultApiModel
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDatabaseService {

    companion object {
        const val BASE_IMAGE_POSTER_URL = "https://image.tmdb.org/t/p/original"
    }

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = BuildConfig.THE_MOVIE_DB_API_KEY,
        @Query("language") language: String = "pt-BR",
        @Query("page") page: Int,
    ): TheMovieDatabaseMovieResultApiModel

}