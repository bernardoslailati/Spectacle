package com.slailati.android.spectacle.data.remote.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.*
import com.slailati.android.spectacle.data.remote.model.Profile
import com.slailati.android.spectacle.data.remote.model.Response
import com.slailati.android.spectacle.data.remote.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FirebaseAuthDataSourceImpl(
    private val firebaseAuth: FirebaseAuth,
) : FirebaseAuthDataSource {

    private val _isUserRegistered = MutableLiveData<Response<User>>()
    private val _isLoggedIn = MutableLiveData<Response<Profile>>()

    override fun registerUser(user: User) {
        val (email, password) = user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val successMessage =
                    "S-P-E-C-T-A-C-L-E!\n\nSeu usuário foi cadastrado com sucesso!\n\nAgora, você já está pronto para entrar e favoritar todos os seus filmes e músicas favoritas."
                _isUserRegistered.postValue(Response(user, true, successMessage))
            }
            .addOnFailureListener { exception ->
                val errorMessage: String = when (exception) {
                    is FirebaseAuthWeakPasswordException -> "Senha precisa de pelo menos 6 dígitos."
                    is FirebaseAuthInvalidCredentialsException -> "O email inserido é inválido."
                    is FirebaseAuthUserCollisionException -> "O email inserido já está cadastrado."
                    else -> "Oops, houve um problema... Tente novamente."
                }
                _isUserRegistered.postValue(Response(user, false, errorMessage))
            }
    }

    override fun login(user: User) {
        val (email, password) = user
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val successMessage = "Login efetuado com sucesso!"
                val userProfile = Profile(
                    uuid = it.user?.uid ?: "?",
                    email = it.user?.email ?: email
                )
                _isLoggedIn.postValue(Response(userProfile, true, successMessage))
            }
            .addOnFailureListener { exception ->
                val errorMessage: String = when (exception) {
                    is FirebaseAuthInvalidCredentialsException -> "A senha inserida é inválida."
                    is FirebaseAuthInvalidUserException -> "O email inserido é inválido."
                    else -> "Oops, houve um problema... Tente novamente."
                }
                _isLoggedIn.postValue(Response(Profile(), false, errorMessage))
            }
            .addOnCompleteListener {
                resetIsLoggedInValue()
            }
    }

    private fun resetIsLoggedInValue() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(200)
            _isLoggedIn.postValue(null)
        }
    }

    override fun logout() {}

    override fun isLoggedIn(): LiveData<Response<Profile>> = _isLoggedIn

    override fun isRegistered(): MutableLiveData<Response<User>> = _isUserRegistered

}