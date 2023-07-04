package com.space.chatapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "message_store")
data class ChatEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val sender: String?,
    val message: String?,
    val time: Long?,
    val isOnline:Boolean = true
)