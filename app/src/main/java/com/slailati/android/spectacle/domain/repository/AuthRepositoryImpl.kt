package com.slailati.android.spectacle.domain.repository

import androidx.lifecycle.LiveData
import com.slailati.android.spectacle.data.remote.datasource.FirebaseAuthDataSource
import com.slailati.android.spectacle.data.remote.model.Profile
import com.slailati.android.spectacle.data.remote.model.Response
import com.slailati.android.spectacle.data.remote.model.User

class AuthRepositoryImpl(
    private val firebaseAuthDatasource: FirebaseAuthDataSource,
) : AuthRepository {

    override fun registerUser(user: User) = firebaseAuthDatasource.registerUser(user)

    override fun login(user: User) = firebaseAuthDatasource.login(user)

    override fun logout() {}

    override fun isLoggedIn(): LiveData<Response<Profile>> = firebaseAuthDatasource.isLoggedIn()

    override fun isRegistered(): LiveData<Response<User>> = firebaseAuthDatasource.isRegistered()

}