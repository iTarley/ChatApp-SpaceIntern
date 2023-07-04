package com.space.chatapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.space.chatapp.data.local.data_store.ThemeDataStoreManager
import org.koin.dsl.module


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "darkMode_preference"
)

private fun provideDataStore(context: Context): DataStore<Preferences> {
    return context.dataStore
}


val dataStoreModule = module {
    single { provideDataStore(get()) }
    single { ThemeDataStoreManager(get()) }
}