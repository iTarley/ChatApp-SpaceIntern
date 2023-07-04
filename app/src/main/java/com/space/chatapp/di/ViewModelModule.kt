package com.space.chatapp.di

import com.space.chatapp.presentation.chat_activity.viewmodel.ChatHolderViewModel
import com.space.chatapp.presentation.chat_screen.viewmodel.ChatViewModel
import com.space.chatapp.presentation.model.mapper.MessageDomainUIMapper
import com.space.chatapp.presentation.model.mapper.MessageUIDomainMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        ChatHolderViewModel(get(), get())
    }

    viewModel {
        ChatViewModel(get(), get(), MessageUIDomainMapper(), MessageDomainUIMapper())
    }
}