package com.slailati.android.spectacle.data.datasource

import androidx.lifecycle.LiveData
import com.slailati.android.spectacle.data.model.Profile
import com.slailati.android.spectacle.data.model.Response
import com.slailati.android.spectacle.data.model.User

interface FirebaseAuthDataSource {

    fun registerUser(user: User): LiveData<Response<User>>

    fun login(user: User)

    fun logout()

    fun isLoggedIn(): LiveData<Response<Profile>>

}