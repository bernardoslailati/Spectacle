package com.slailati.android.spectacle.data.remote.service

import com.slailati.android.spectacle.BuildConfig
import com.slailati.android.spectacle.data.local.model.DeezerMusicsResultApiModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface DeezerService {

    @Headers(*[
        "x-rapidapi-host: deezerdevs-deezer.p.rapidapi.com",
        "x-rapidapi-key: ${BuildConfig.DEEZER_API_KEY}"
    ])
    @GET("search")
    suspend fun getMusics(
        @Query("q") searchFor: String = "a",
    ): DeezerMusicsResultApiModel

}