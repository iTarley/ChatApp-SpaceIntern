package com.space.chatapp.data.repository

import com.space.chatapp.data.local.data_store.ThemeDataStoreManager
import com.space.chatapp.domain.local.repository.ThemeDataStoreRepository
import com.space.chatapp.utils.ChatThemeMode
import kotlinx.coroutines.flow.firstOrNull
/**
 * This class is used to handle the data store operations
 */
class ThemeDataStoreRepositoryImpl(private val dataStorePreferences: ThemeDataStoreManager) :
    ThemeDataStoreRepository {

    override suspend fun putThemeStateValue(mode: ChatThemeMode) {
        Result.runCatching {
            dataStorePreferences.saveValue(STRING_KEY,mode)
        }
    }

    override suspend fun getThemeStateValue(): Result<String> {
        return Result.runCatching {
            val flow = dataStorePreferences.getValue(STRING_KEY)
            flow.firstOrNull() ?: ""
        }
    }

    companion object {
        private const val STRING_KEY = "string_key"
    }
}