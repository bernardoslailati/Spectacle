package com.slailati.android.spectacle.ui.extension

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext

fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    val activeNetworkInfo = connectivityManager!!.activeNetworkInfo

    if (!(activeNetworkInfo != null && activeNetworkInfo.isConnected))
        Toast.makeText(this,
            "Necessário ativar internet para realizar essa ação.",
            Toast.LENGTH_SHORT).show()

    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}