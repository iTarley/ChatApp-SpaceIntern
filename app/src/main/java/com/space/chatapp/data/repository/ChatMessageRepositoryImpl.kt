package com.space.chatapp.data.repository

import com.space.chatapp.data.local.dao.ChatDao
import com.space.chatapp.data.mapper.MessageDomainEntityMapper
import com.space.chatapp.data.mapper.MessageEntityDomainMapper
import com.space.chatapp.domain.local.repository.ChatMessageRepository
import com.space.chatapp.domain.model.MessageModel
import kotlinx.coroutines.flow.map
/**
 * This class is used to handle the database operations
 */
class ChatMessageRepositoryImpl(
    private val dao: ChatDao,
    private val domainEntityMapper: MessageDomainEntityMapper,
    private val entityDomainMapper: MessageEntityDomainMapper
) : ChatMessageRepository {

    override fun getAllMessages(userId: String) = dao.getAllMessages().map { chat ->
        chat.filter {
            (it.sender == userId || it.isOnline)
        }.map { entityDomainMapper(it) }
    }

    override suspend fun insertMessage(message: MessageModel) {
        dao.insertMessage(domainEntityMapper(message))
    }
}