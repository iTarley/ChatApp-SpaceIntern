package com.space.chatapp.domain.usecase.message.show

import com.space.chatapp.domain.model.MessageModel
import kotlinx.coroutines.flow.Flow

interface ShowMessageUseCase {
    fun invoke(userId: String): Flow<List<MessageModel>>
}