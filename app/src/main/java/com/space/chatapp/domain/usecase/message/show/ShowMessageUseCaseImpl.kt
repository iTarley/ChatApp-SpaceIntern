package com.space.chatapp.domain.usecase.message.show

import com.space.chatapp.domain.local.repository.ChatMessageRepository
import com.space.chatapp.domain.model.MessageModel
import kotlinx.coroutines.flow.Flow

class ShowMessageUseCaseImpl(private val repository: ChatMessageRepository) : ShowMessageUseCase {

    override fun invoke(userId: String): Flow<List<MessageModel>> {
        return repository.getAllMessages(userId)
    }
}