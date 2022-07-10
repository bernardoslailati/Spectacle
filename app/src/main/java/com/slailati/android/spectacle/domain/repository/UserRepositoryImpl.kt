package com.slailati.android.spectacle.domain.repository

import com.slailati.android.spectacle.data.local.datasource.ProfileDataSource
import com.slailati.android.spectacle.data.remote.model.Profile

class UserRepositoryImpl(
    private val profileDataSource: ProfileDataSource
): UserRepository {

    override suspend fun insertProfile(profile: Profile) {
        profileDataSource.insertProfile(profile)
    }

    override suspend fun isLogged(): Boolean =
        profileDataSource.isLogged()

    override suspend fun logout(): Boolean =
        profileDataSource.logout()

}