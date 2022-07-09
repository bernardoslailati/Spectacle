package com.slailati.android.spectacle.data.remote.model

data class Response<T> (
    val value: T,
    val success: Boolean = false,
    val message: String = ""
)