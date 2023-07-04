package com.space.chatapp.presentation.chat_screen.viewmodel

import androidx.lifecycle.ViewModel
import com.space.chatapp.domain.usecase.message.send.SendMessageUseCase
import com.space.chatapp.domain.usecase.message.show.ShowMessageUseCase
import com.space.chatapp.presentation.model.Message
import com.space.chatapp.presentation.model.mapper.MessageDomainUIMapper
import com.space.chatapp.presentation.model.mapper.MessageUIDomainMapper
import com.space.chatapp.utils.extension.convertTimeToPattern
import com.space.chatapp.utils.extension.getTimeInMills
import com.space.chatapp.utils.extension.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * ViewModel for chat screen
 */

class ChatViewModel(
    private val sendMessageUseCase: SendMessageUseCase,
    private val showMessageUseCase: ShowMessageUseCase,
    private val uiDomainMapper: MessageUIDomainMapper,
    private val domainUiMapper: MessageDomainUIMapper
) : ViewModel() {

    /**
     * A function that takes a user ID as input and returns a flow of a list of messages.
     * This function uses the showMessageUseCase to fetch message models and then maps them
     * using the domainUiMapper to convert them into UI-specific models.
     * @param userId The ID of the user whose messages are being fetched
     * @return A flow of a list of messages
     */
    fun showMessages(userId: String): Flow<List<Message>> = showMessageUseCase.invoke(userId)
        .map { messageModels ->
            messageModels.map { domainUiMapper(it) }
        }

    /**
     * A function that takes a message, sender, and online status as input and sends the message.
     * This function uses the sendMessageUseCase to send the message.
     * @param editTextInput The message to be sent
     * @param sender The ID of the user who is sending the message
     * @param isOnline The online status of the user who is sending the message
     * @return A flow of a list of messages
     */
    fun sendMessage(editTextInput: String, sender: String,isOnline:Boolean) {
        if (editTextInput.isNotEmpty()) {
            viewModelScope(IO) {
                val message = Message(
                    sender = sender,
                    message = editTextInput,
                    time = getTimeInMills().convertTimeToPattern(),
                    isOnline = isOnline
                )
                sendMessageUseCase.invoke(uiDomainMapper(message))
            }
        }
    }
}