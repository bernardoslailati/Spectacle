package com.slailati.android.spectacle.data.model

data class Profile (
    val uuid: String = "",
    val hasSession: Boolean = false,
    val expiresAt: Long = 0L,
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val city: String = "",
    val state: String = "",
    val country: String = ""
)