package com.space.chatapp.domain.usecase.theme.save

import com.space.chatapp.utils.ChatThemeMode

interface SaveThemeStateUseCase {
    suspend fun invoke(themeMode: ChatThemeMode)
}