package com.space.chatapp.domain.local.repository

import com.space.chatapp.domain.model.MessageModel
import kotlinx.coroutines.flow.Flow
/**
 * This interface is used to handle the database operations
 */
interface ChatMessageRepository {

    fun getAllMessages(userId: String): Flow<List<MessageModel>>

    suspend fun insertMessage(message: MessageModel)
}