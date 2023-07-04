package com.space.chatapp.domain.usecase.message.send

import com.space.chatapp.domain.model.MessageModel

interface SendMessageUseCase {
    suspend fun invoke(chat: MessageModel)
}