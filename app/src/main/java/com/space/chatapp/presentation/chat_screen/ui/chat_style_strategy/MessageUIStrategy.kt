package com.space.chatapp.presentation.chat_screen.ui.chat_style_strategy


import com.space.chatapp.databinding.ChatMessageViewBinding
import com.space.chatapp.presentation.model.Message

/**
 * Abstract Strategy for setting ui elements
 */

interface MessageUIStrategy {
    fun setUiElements(binding: ChatMessageViewBinding, item: Message)
}

