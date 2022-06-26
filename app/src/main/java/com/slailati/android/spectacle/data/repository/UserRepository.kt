package com.slailati.android.spectacle.data.repository

import com.slailati.android.spectacle.data.model.Profile

interface UserRepository {

    suspend fun insertProfile(profile: Profile)

    suspend fun isLogged(): Boolean

    suspend fun logout(): Boolean

}