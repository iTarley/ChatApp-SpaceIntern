package com.space.chatapp.presentation.model.mapper

import com.space.chatapp.domain.model.MessageModel
import com.space.chatapp.presentation.model.Message
import com.space.chatapp.utils.extension.convertTimeToLong
import com.space.chatapp.utils.extension.convertTimeToPattern
import com.space.chatapp.utils.mapper.ModelMapper

class MessageDomainUIMapper : ModelMapper<MessageModel, Message> {
    override operator fun invoke(model: MessageModel): Message =
        Message(
            message = model.message,
            time = model.time?.convertTimeToPattern(),
            sender = model.sender,
            isOnline = model.isOnline,
            id = model.id
        )
}