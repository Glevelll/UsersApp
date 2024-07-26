package com.project.usersapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    suspend fun insertUsers(users: List<UsersEntity>)

    @Query("SELECT * FROM UsersEntity WHERE email = :email")
    suspend fun getUserByEmail(email: String): UsersEntity?

    @Query("DELETE FROM UsersEntity")
    suspend fun clearAll()

    @Query("SELECT * FROM UsersEntity")
    suspend fun getUsers(): List<UsersEntity>

    @Query("DELETE FROM UsersEntity WHERE email = :email")
    suspend fun deleteUserByEmail(email: String)
}