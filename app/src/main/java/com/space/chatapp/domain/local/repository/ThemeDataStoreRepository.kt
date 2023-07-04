package com.space.chatapp.domain.local.repository

import com.space.chatapp.utils.ChatThemeMode
/**
 * This interface is used to handle the data store operations
 */
interface ThemeDataStoreRepository {
    suspend fun putThemeStateValue(
        mode: ChatThemeMode
    )

    suspend fun getThemeStateValue(): Result<String>
}