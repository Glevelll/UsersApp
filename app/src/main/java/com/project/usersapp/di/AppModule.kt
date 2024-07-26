package com.project.usersapp.di

import android.content.Context
import androidx.room.Room
import com.project.usersapp.data.local.UsersDatabase
import com.project.usersapp.data.remote.UsersApi
import com.project.usersapp.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext context: Context): UsersDatabase {
        return Room.databaseBuilder(context, UsersDatabase::class.java, "users.db").build()
    }

    @Provides
    @Singleton
    fun provideUserApi(): UsersApi {
        val httpLoginInterceptor = HttpLoggingInterceptor()
        httpLoginInterceptor.level = HttpLoggingInterceptor.Level.BASIC

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoginInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(UsersApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideUserRepository(api: UsersApi, db: UsersDatabase): UserRepository {
        return UserRepository(api, db)
    }
}