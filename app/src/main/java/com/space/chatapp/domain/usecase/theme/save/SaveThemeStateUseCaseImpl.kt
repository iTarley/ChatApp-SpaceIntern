package com.space.chatapp.domain.usecase.theme.save

import com.space.chatapp.domain.local.repository.ThemeDataStoreRepository
import com.space.chatapp.utils.ChatThemeMode

class SaveThemeStateUseCaseImpl(private val themeDataStoreRepository: ThemeDataStoreRepository) :
    SaveThemeStateUseCase {

    override suspend fun invoke(themeMode: ChatThemeMode) {
        return themeDataStoreRepository.putThemeStateValue(themeMode)
    }
}