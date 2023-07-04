package com.space.chatapp.presentation.model

data class Message(
    val id: Int? = null,
    val sender: String?,
    val message: String?,
    val time: String?,
    val isOnline: Boolean = true
)
