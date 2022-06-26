package com.slailati.android.spectacle.ui.viewmodel

import androidx.lifecycle.LiveData
import com.slailati.android.spectacle.data.model.Profile
import com.slailati.android.spectacle.data.model.Response
import com.slailati.android.spectacle.data.model.User
import com.slailati.android.spectacle.data.repository.AuthRepository

class UserViewModel(
    private val firebaseAuthRepository: AuthRepository
): BaseViewModel() {

    private lateinit var _isUserRegistered: LiveData<Response<User>>
    fun isUserRegistered() = _isUserRegistered

    private lateinit var _isLoggedIn: LiveData<Response<Profile>>
    fun isLoggedIn() = _isLoggedIn

    fun registerUser(user: User) {
        _isUserRegistered = firebaseAuthRepository.registerUser(user)
    }

    fun login(user: User) {
        _isLoggedIn = firebaseAuthRepository.login(user)
    }

    fun logout() {

    }

}