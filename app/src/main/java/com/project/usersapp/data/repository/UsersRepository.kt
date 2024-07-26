package com.project.usersapp.data.repository

import com.project.usersapp.data.local.UsersDatabase
import com.project.usersapp.data.local.UsersEntity
import com.project.usersapp.data.mapper.toUsersEntity
import com.project.usersapp.data.remote.UsersApi
import com.project.usersapp.domain.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: UsersApi,
    private val db: UsersDatabase
) {
    suspend fun refreshUsers() {
        val response = api.getUsers(UsersApi.PAGE_SIZE)
        if (response.isSuccessful) {
            response.body()?.results?.let { users ->
                db.dao.clearAll()
                db.dao.insertUsers(users.map { it.toUsersEntity() })
            }
        }
    }

    suspend fun getUsers(): List<UsersEntity> {
        return db.dao.getUsers()
    }

    suspend fun deleteUser(email: String) {
        return db.dao.deleteUserByEmail(email)
    }

    suspend fun getUserByEmail(email: String): UsersEntity? {
        return db.dao.getUserByEmail(email)
    }

    suspend fun addUser(user: User) {
        db.dao.insertUsers(listOf(user.toUsersEntity()))
    }
}