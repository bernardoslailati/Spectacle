package com.slailati.android.spectacle.domain.repository

import androidx.lifecycle.LiveData
import com.slailati.android.spectacle.data.remote.model.Profile
import com.slailati.android.spectacle.data.remote.model.Response
import com.slailati.android.spectacle.data.remote.model.User

interface AuthRepository {

    fun registerUser(user: User)

    fun login(user: User)

    fun logout()

    fun isLoggedIn(): LiveData<Response<Profile>>

    fun isRegistered(): LiveData<Response<User>>

}