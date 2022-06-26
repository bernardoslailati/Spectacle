package com.slailati.android.spectacle.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.slailati.android.spectacle.data.model.Profile
import com.slailati.android.spectacle.data.model.Response
import com.slailati.android.spectacle.data.model.User
import com.slailati.android.spectacle.data.repository.AuthRepository
import com.slailati.android.spectacle.data.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserRepository,
    private val firebaseAuthRepository: AuthRepository
): BaseViewModel() {

    private lateinit var _isUserRegistered: LiveData<Response<User>>
    fun isUserRegistered() = _isUserRegistered

    private lateinit var _isLoggedIn: LiveData<Response<Profile>>
    fun isLoggedIn() = _isLoggedIn

    private val _isAlreadyLoggedIn: MutableSharedFlow<Boolean> = MutableSharedFlow()
    fun isAlreadyLoggedIn() = _isAlreadyLoggedIn.asSharedFlow()

    fun registerUser(user: User) {
        _isUserRegistered = firebaseAuthRepository.registerUser(user)
    }

    fun insertProfile(profile: Profile) {
        viewModelScope.launch {
            userRepository.insertProfile(profile)
        }
    }

    fun checkIfIsAlreadyLogged() {
        CoroutineScope(Dispatchers.Main).launch {
            _isAlreadyLoggedIn.emit(userRepository.isLogged())
        }
    }

    fun login(user: User) {
        _isLoggedIn = firebaseAuthRepository.login(user)
    }

    fun logout() {
        viewModelScope.launch {
            userRepository.logout()
        }
    }

}