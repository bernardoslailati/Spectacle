package com.slailati.android.spectacle.data.local.datasource

import com.slailati.android.spectacle.data.remote.model.Profile

interface ProfileDataSource {

    suspend fun insertProfile(profile: Profile)

    suspend fun isLogged(): Boolean

    suspend fun logout(): Boolean

}