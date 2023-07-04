package com.space.chatapp.presentation.model.mapper

import com.space.chatapp.domain.model.MessageModel
import com.space.chatapp.presentation.model.Message
import com.space.chatapp.utils.extension.convertTimeToLong
import com.space.chatapp.utils.mapper.ModelMapper

class MessageUIDomainMapper : ModelMapper<Message, MessageModel> {
    override operator fun invoke(model: Message): MessageModel =
        MessageModel(
            message = model.message,
            time = model.time?.let { convertTimeToLong() },
            sender = model.sender,
            isOnline = model.isOnline,
            id = model.id
        )
}