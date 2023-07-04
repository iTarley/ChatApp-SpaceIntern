package com.space.chatapp.utils

import androidx.appcompat.app.AppCompatDelegate

enum class ChatThemeMode {
    DAY_MODE, NIGHT_MODE
}

fun ChatThemeMode.toAppCompatMode(): Int {
    return when (this) {
        ChatThemeMode.DAY_MODE -> AppCompatDelegate.MODE_NIGHT_NO
        ChatThemeMode.NIGHT_MODE -> AppCompatDelegate.MODE_NIGHT_YES
    }
}