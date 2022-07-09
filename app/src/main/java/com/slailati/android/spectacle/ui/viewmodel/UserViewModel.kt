package com.slailati.android.spectacle.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.slailati.android.spectacle.data.remote.model.Profile
import com.slailati.android.spectacle.data.remote.model.User
import com.slailati.android.spectacle.data.repository.AuthRepository
import com.slailati.android.spectacle.data.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserRepository,
    private val firebaseAuthRepository: AuthRepository,
) : BaseViewModel() {

    fun isUserRegistered() = firebaseAuthRepository.isRegistered()
    fun isLoggedIn() = firebaseAuthRepository.isLoggedIn()

    fun registerUser(user: User) = firebaseAuthRepository.registerUser(user)
    fun login(user: User) = firebaseAuthRepository.login(user)
    fun logout() {
        viewModelScope.launch {
            userRepository.logout()
        }
    }

    fun insertProfile(profile: Profile) {
        CoroutineScope(Dispatchers.Main).launch {
            userRepository.insertProfile(profile)
        }
    }

    private val _isAlreadyLoggedIn: MutableSharedFlow<Boolean> = MutableSharedFlow()
    fun isAlreadyLoggedIn() = _isAlreadyLoggedIn.asSharedFlow()

    fun checkIfIsAlreadyLoggedIn() {
        CoroutineScope(Dispatchers.Main).launch {
            _isAlreadyLoggedIn.emit(userRepository.isLogged())
        }
    }

}