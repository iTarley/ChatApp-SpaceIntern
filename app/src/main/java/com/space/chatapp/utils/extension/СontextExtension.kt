package com.space.chatapp.utils.extension

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

/**
 * Extension function for checking network availability
 */
fun Context.isNetworkAvailable(): Boolean {

    val connectivityManager =
        applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        capabilities?.run {
            hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || hasTransport(
                NetworkCapabilities.TRANSPORT_WIFI
            ) || hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
        } ?: false
    } else {
        return false
    }
}