package com.slailati.android.spectacle.data.repository

import androidx.lifecycle.LiveData
import com.slailati.android.spectacle.data.datasource.FirebaseAuthDataSource
import com.slailati.android.spectacle.data.datasource.ProfileDataSource
import com.slailati.android.spectacle.data.model.Profile
import com.slailati.android.spectacle.data.model.Response
import com.slailati.android.spectacle.data.model.User

class AuthRepositoryImpl (
    private val firebaseAuthDatasource: FirebaseAuthDataSource
) : AuthRepository {

    override fun registerUser(user: User): LiveData<Response<User>> =
        firebaseAuthDatasource.registerUser(user)

    override fun login(user: User): LiveData<Response<Profile>> =
        firebaseAuthDatasource.login(user)

    override fun logout() {
        // TODO("Not yet implemented")
    }

}