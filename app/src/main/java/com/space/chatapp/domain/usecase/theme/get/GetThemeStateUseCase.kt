package com.space.chatapp.domain.usecase.theme.get


interface GetThemeStateUseCase {
    suspend fun invoke(): Result<String>
}