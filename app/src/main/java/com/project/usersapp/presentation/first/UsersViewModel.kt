package com.project.usersapp.presentation.first

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.usersapp.data.mapper.toUser
import com.project.usersapp.data.repository.UserRepository
import com.project.usersapp.domain.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users.asStateFlow()
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            refreshUsers()
        }

        viewModelScope.launch {
            _searchQuery.collectLatest { query ->
                _isLoading.value = true
                val usersEntity = repository.getUsers()
                _users.value = usersEntity.filter { userEntity ->
                    userEntity.firstName.contains(query, ignoreCase = true) ||
                            userEntity.lastName.contains(query, ignoreCase = true)
                }.map { it.toUser() }
                _isLoading.value = false
            }
        }
    }

    private suspend fun refreshUsers() {
        _isLoading.value = true
        repository.refreshUsers()
        val usersEntity = repository.getUsers()
        _users.value = usersEntity.map { it.toUser() }
        _isLoading.value = false
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun deleteUser(email: String) {
        viewModelScope.launch {
            repository.deleteUser(email)
            _users.value = _users.value.filter { it.email != email }
        }
    }

    fun addUser(user: User) {
        viewModelScope.launch {
            repository.addUser(user)
            _users.value += listOf(user)
        }
    }
}