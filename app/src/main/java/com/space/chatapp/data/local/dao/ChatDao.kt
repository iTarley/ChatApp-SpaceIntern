package com.space.chatapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.space.chatapp.data.local.entity.ChatEntity
import kotlinx.coroutines.flow.Flow
/**
 * This interface is used to handle the database operations
 */
@Dao
interface ChatDao {
    @Query("SELECT * FROM message_store")
    fun getAllMessages(): Flow<List<ChatEntity>>

    @Insert
    suspend fun insertMessage(vararg message: ChatEntity)
}