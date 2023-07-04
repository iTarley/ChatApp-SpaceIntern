package com.space.chatapp.domain.model

data class MessageModel(
    val id: Int? = null,
    val sender: String?,
    val message: String?,
    val time: Long?,
    val isOnline:Boolean = true
)
