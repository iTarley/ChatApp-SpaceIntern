package com.space.chatapp.presentation.chat_screen.ui.fragment

import com.space.chatapp.presentation.chat_activity.activity.ChatHolderActivity
import com.space.chatapp.presentation.chat_screen.ui.base.BaseChatFragment

class ChatFragment : BaseChatFragment() {
    override fun userId(): String = arguments?.getString(ChatHolderActivity.USER_ID) ?: ""
}