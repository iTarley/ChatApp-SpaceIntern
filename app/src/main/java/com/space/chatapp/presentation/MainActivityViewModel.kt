package com.space.chatapp.presentation

import androidx.lifecycle.ViewModel
import com.space.chatapp.domain.use_case.GetThemeStateUseCase
import com.space.chatapp.domain.use_case.SaveThemeStateUseCase
import com.space.chatapp.presentation.model.StateHolder
import com.space.chatapp.utils.ThemeModeEnum
import com.space.chatapp.utils.extension.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class MainActivityViewModel(
    private val saveThemeStateUseCase: SaveThemeStateUseCase,
    private val getThemeStateUseCase: GetThemeStateUseCase
) :
    ViewModel() {

    private val _state = MutableSharedFlow<StateHolder>()
    val state = _state.asSharedFlow()

    private suspend fun getThemeStateValue(): Result<String> = getThemeStateUseCase.invoke()

    private fun saveThemeStateValue(dayMode: ThemeModeEnum) {
        viewModelScope {
            saveThemeStateUseCase.invoke(dayMode)
        }
    }


    fun dayNightHandling() {
        viewModelScope {
            val mode = getThemeStateValue()
            if (mode.getOrNull() == ThemeModeEnum.DAY_MODE.mode) {
                _state.emit(StateHolder(ThemeModeEnum.NIGHT_MODE.mode))
                saveThemeStateValue(ThemeModeEnum.NIGHT_MODE)
            } else {
                _state.emit(StateHolder(ThemeModeEnum.DAY_MODE.mode))
                saveThemeStateValue(ThemeModeEnum.DAY_MODE)
            }
        }
    }

    fun checkPreferencesStatus() {
        viewModelScope {
            val mode = getThemeStateValue()
            if (mode.getOrNull() == ThemeModeEnum.DAY_MODE.mode) {
                _state.emit(StateHolder(ThemeModeEnum.DAY_MODE.mode))
            } else {
                _state.emit(StateHolder(ThemeModeEnum.NIGHT_MODE.mode))
            }
        }
    }
}