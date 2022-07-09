package com.slailati.android.spectacle.data.repository

import com.slailati.android.spectacle.data.remote.model.Profile

interface UserRepository {

    suspend fun insertProfile(profile: Profile)

    suspend fun isLogged(): Boolean

    suspend fun logout(): Boolean

}