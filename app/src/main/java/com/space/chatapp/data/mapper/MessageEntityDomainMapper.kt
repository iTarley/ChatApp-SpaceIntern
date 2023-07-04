package com.space.chatapp.data.mapper

import com.space.chatapp.data.local.entity.ChatEntity
import com.space.chatapp.domain.model.MessageModel
import com.space.chatapp.utils.mapper.ModelMapper

class MessageEntityDomainMapper : ModelMapper<ChatEntity, MessageModel> {
    override operator fun invoke(model: ChatEntity): MessageModel =
        MessageModel(
            id = model.id,
            sender = model.sender,
            message = model.message,
            time = model.time,
            isOnline = model.isOnline
        )
}