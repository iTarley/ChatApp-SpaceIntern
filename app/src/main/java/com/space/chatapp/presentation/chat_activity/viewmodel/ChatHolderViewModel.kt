package com.space.chatapp.presentation.chat_activity.viewmodel

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import com.space.chatapp.domain.usecase.theme.get.GetThemeStateUseCase
import com.space.chatapp.domain.usecase.theme.save.SaveThemeStateUseCase
import com.space.chatapp.utils.ChatThemeMode
import com.space.chatapp.utils.extension.viewModelScope
import com.space.chatapp.utils.toAppCompatMode
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

/**
 * This view model is used to handle the theme state and save it to data store
 */
class ChatHolderViewModel(
    private val saveThemeStateUseCase: SaveThemeStateUseCase,
    private val getThemeStateUseCase: GetThemeStateUseCase
) :
    ViewModel() {

    private val _state = MutableSharedFlow<ChatThemeMode>()
    val state = _state.asSharedFlow()

    /**
     * This suspend function is used to get the theme state value from data store
     */
    private suspend fun getThemeStateValue(): Result<String> = getThemeStateUseCase.invoke()

    /**
     * This suspend function is used to save the theme state value to data store
     */
    private fun saveThemeStateValue(themeMode: ChatThemeMode) {
        viewModelScope {
            saveThemeStateUseCase.invoke(themeMode)
            AppCompatDelegate.setDefaultNightMode(themeMode.toAppCompatMode())
        }
    }

    /**
     * This function is used to update the theme state
     * If the theme state is DAY_MODE then it will update it to NIGHT_MODE
     * If the theme state is NIGHT_MODE then it will update it to DAY_MODE
     * And save the updated theme state to data store
     */
    fun themeUpdate() {
        viewModelScope {
            when (getThemeStateValue().getOrNull()) {
                ChatThemeMode.DAY_MODE.name -> {
                    _state.emit(ChatThemeMode.NIGHT_MODE)
                    saveThemeStateValue(ChatThemeMode.NIGHT_MODE)
                }
                else -> {
                    _state.emit(ChatThemeMode.DAY_MODE)
                    saveThemeStateValue(ChatThemeMode.DAY_MODE)
                }
            }
        }
    }

    /**
     * This function is used to check the theme state value from data store
     * And update the theme state
     * If the theme state is DAY_MODE then it will update it to DAY_MODE
     * If the theme state is NIGHT_MODE then it will update it to NIGHT_MODE
     * And save the updated theme state to data store
     */
    fun checkPreferencesStatus() {
        viewModelScope {
            when (getThemeStateValue().getOrNull()) {
                ChatThemeMode.DAY_MODE.name -> {
                    _state.emit(ChatThemeMode.DAY_MODE)
                    AppCompatDelegate.setDefaultNightMode(ChatThemeMode.DAY_MODE.toAppCompatMode())
                }
                else -> {
                    _state.emit(ChatThemeMode.NIGHT_MODE)
                    AppCompatDelegate.setDefaultNightMode(ChatThemeMode.NIGHT_MODE.toAppCompatMode())
                }
            }
        }
    }
}