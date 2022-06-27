package com.slailati.android.spectacle.ui.extension

import android.util.Patterns
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.slailati.android.spectacle.databinding.FragmentLoginBinding
import com.slailati.android.spectacle.databinding.FragmentRegisterBinding

fun FragmentLoginBinding.isValidCredentials(): Boolean {
    if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.text.toString()).matches()) {
        etEmail.error = "O email inserido é inválido."
        return false
    }
    if (etPassword.text.toString().length < 6) {
        etPassword.error = "A senha deve conter pelo menos 6 caracteres."
        return false
    }
    return true
}

fun FragmentLoginBinding.resetErrorMessages() {
    etEmail.error = null
    etPassword.error = null
}