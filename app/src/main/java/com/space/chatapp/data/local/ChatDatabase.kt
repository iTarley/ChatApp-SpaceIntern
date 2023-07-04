package com.space.chatapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.space.chatapp.data.local.dao.ChatDao
import com.space.chatapp.data.local.entity.ChatEntity
/**
 * This class is used to create the database
 */
@Database(
    entities = [ChatEntity::class],
    version = 1,
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun chatDao(): ChatDao
}