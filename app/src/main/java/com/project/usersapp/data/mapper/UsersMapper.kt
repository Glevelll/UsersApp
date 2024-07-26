package com.project.usersapp.data.mapper

import com.project.usersapp.data.local.UsersEntity
import com.project.usersapp.data.remote.UsersDto
import com.project.usersapp.domain.User

fun UsersDto.toUsersEntity(): UsersEntity {
    return UsersEntity(
        email = email,
        gender = gender,
        firstName = name.first,
        lastName = name.last,
        city = location.city,
        country = location.country
    )
}

fun UsersEntity.toUser(): User {
    return User(
        email = email,
        gender = gender,
        firstName = firstName,
        lastName = lastName,
        city = city,
        country = country
    )
}

fun User.toUsersEntity() = UsersEntity(
    email = email,
    gender = gender,
    firstName = firstName,
    lastName = lastName,
    city = city,
    country = country
)