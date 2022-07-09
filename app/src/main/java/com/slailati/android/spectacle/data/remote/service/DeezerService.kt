package com.slailati.android.spectacle.data.remote.service

import com.slailati.android.spectacle.data.local.model.DeezerMusicsResultApiModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface DeezerService {

    @Headers(*[
        "x-rapidapi-host: deezerdevs-deezer.p.rapidapi.com",
        "x-rapidapi-key: f75567ab2cmsh98cc4d1f2f2349bp127ab5jsn180f822350b5"
    ])
    @GET("search")
    suspend fun getMusics(
        @Query("q") searchFor: String = "a",
    ): DeezerMusicsResultApiModel

}