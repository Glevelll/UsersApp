package com.project.usersapp.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersApi {
    @GET("api/")
    suspend fun getUsers(@Query("results") count: Int): Response<ApiResponse>

    companion object {
        const val BASE_URL = "https://randomuser.me/"
        const val PAGE_SIZE = 20
    }
}

data class ApiResponse(
    val results: List<UsersDto>
)