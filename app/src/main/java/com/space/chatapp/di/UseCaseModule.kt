package com.space.chatapp.di

import com.space.chatapp.domain.usecase.message.send.SendMessageUseCase
import com.space.chatapp.domain.usecase.message.send.SendMessageUseCaseImpl
import com.space.chatapp.domain.usecase.message.show.ShowMessageUseCase
import com.space.chatapp.domain.usecase.message.show.ShowMessageUseCaseImpl
import com.space.chatapp.domain.usecase.theme.get.GetThemeStateUseCase
import com.space.chatapp.domain.usecase.theme.get.GetThemeStateUseCaseImpl
import com.space.chatapp.domain.usecase.theme.save.SaveThemeStateUseCase
import com.space.chatapp.domain.usecase.theme.save.SaveThemeStateUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    single<SaveThemeStateUseCase> { SaveThemeStateUseCaseImpl(get()) }
    single<GetThemeStateUseCase> { GetThemeStateUseCaseImpl(get()) }
    single<SendMessageUseCase> { SendMessageUseCaseImpl(get()) }
    single<ShowMessageUseCase> { ShowMessageUseCaseImpl(get()) }
}
