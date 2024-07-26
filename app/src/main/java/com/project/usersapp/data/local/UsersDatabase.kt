package com.project.usersapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UsersEntity::class], version = 1)
abstract class UsersDatabase: RoomDatabase() {
    abstract val dao: UserDao
}