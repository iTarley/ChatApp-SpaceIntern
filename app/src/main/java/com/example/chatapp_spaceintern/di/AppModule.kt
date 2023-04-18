package com.example.chatapp_spaceintern.di

import com.example.chatapp_spaceintern.data.repository.ChatMessageRepositoryImpl
import com.example.chatapp_spaceintern.data.repository.DataStoreRepositoryImpl
import com.example.chatapp_spaceintern.domain.local.repository.ChatMessageRepository
import com.example.chatapp_spaceintern.domain.local.repository.DataStoreRepository
import com.example.chatapp_spaceintern.presentation.MainActivityViewModel
import com.example.chatapp_spaceintern.presentation.ui.SharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    includes(dataBaseModule)
    includes(useCaseModule)
    includes(dataStoreModule)

    single<ChatMessageRepository> { ChatMessageRepositoryImpl(get()) }
    single<DataStoreRepository> { DataStoreRepositoryImpl(get()) }

    viewModel {
        SharedViewModel(get(), get())
    }

    viewModel {
        MainActivityViewModel(get())
    }
}


