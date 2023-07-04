package com.space.chatapp.di

import com.space.chatapp.data.mapper.MessageDomainEntityMapper
import com.space.chatapp.data.mapper.MessageEntityDomainMapper
import com.space.chatapp.data.repository.ChatMessageRepositoryImpl
import com.space.chatapp.data.repository.ThemeDataStoreRepositoryImpl
import com.space.chatapp.domain.local.repository.ChatMessageRepository
import com.space.chatapp.domain.local.repository.ThemeDataStoreRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ChatMessageRepository> { ChatMessageRepositoryImpl(get(), MessageDomainEntityMapper(),
        MessageEntityDomainMapper()
    ) }
    single<ThemeDataStoreRepository> { ThemeDataStoreRepositoryImpl(get()) }
}