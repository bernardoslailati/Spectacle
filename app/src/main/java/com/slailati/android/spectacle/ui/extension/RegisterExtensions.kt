package com.slailati.android.spectacle.ui.extension

import android.util.Patterns
import com.slailati.android.spectacle.databinding.FragmentRegisterBinding

fun FragmentRegisterBinding.isValidCredentials(): Boolean {
    if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.text.toString()).matches()) {
        etEmail.error = "O email inserido é inválido."
        return false
    }
    if (etPassword.text.toString().length < 6) {
        etPassword.error = "Senha deve conter pelo menos 6 caracteres."
        return false
    }
    else {
        if (etPassword.text.toString() != etConfirmPassword.text.toString()) {
            etConfirmPassword.error = "A senha confirmada é diferente da senha inicial."
            return false
        }
    }
    return true
}

fun FragmentRegisterBinding.resetErrorMessages() {
    etEmail.error = null
    etPassword.error = null
    etConfirmPassword.error = null
}