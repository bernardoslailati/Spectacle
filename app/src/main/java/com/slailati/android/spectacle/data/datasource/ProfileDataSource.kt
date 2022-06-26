package com.slailati.android.spectacle.data.datasource

import com.slailati.android.spectacle.data.model.Profile

interface ProfileDataSource {

    suspend fun insertProfile(profile: Profile)

    suspend fun isLogged(): Boolean

    suspend fun logout(): Boolean

}