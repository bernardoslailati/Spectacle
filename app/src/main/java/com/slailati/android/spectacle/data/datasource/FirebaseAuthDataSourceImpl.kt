package com.slailati.android.spectacle.data.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.*
import com.slailati.android.spectacle.data.model.Profile
import com.slailati.android.spectacle.data.model.Response
import com.slailati.android.spectacle.data.model.User

class FirebaseAuthDataSourceImpl(
    private val firebaseAuth: FirebaseAuth,
) : FirebaseAuthDataSource {

    private val _isUserRegistered = MutableLiveData<Response<User>>()
    private val _isLoggedIn = MutableLiveData<Response<Profile>>()

    override fun registerUser(user: User): LiveData<Response<User>> {
        val (email, password) = user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val successMessage =
                    "S-P-E-C-T-A-C-L-E!\n\nSeu usuário foi cadastrado com sucesso.\n\nAgora, você já está pronto para entrar e favoritar todos os seus filmes e músicas favoritas..."
                _isUserRegistered.postValue(Response(user, true, successMessage))
            }
            .addOnFailureListener { exception ->
                val errorMessage: String = when (exception) {
                    is FirebaseAuthWeakPasswordException -> "Senha precisa de pelo menos 6 dígitos."
                    is FirebaseAuthInvalidCredentialsException -> "E-mail inválido."
                    is FirebaseAuthUserCollisionException -> "E-mail já cadastrado."
                    else -> "Erro desconhecido."
                }
                _isUserRegistered.postValue(Response(user, false, errorMessage))
            }
        return _isUserRegistered
    }

    override fun login(user: User): LiveData<Response<Profile>> {
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
                    is FirebaseAuthInvalidCredentialsException -> "Senha inválida."
                    is FirebaseAuthInvalidUserException -> "E-mail inválido."
                    else -> "Erro desconhecido."
                }
                _isLoggedIn.postValue(Response(Profile(), false, errorMessage))
            }
        return _isLoggedIn
    }

    override fun logout() {
        // TODO("Not yet implemented")
    }
}