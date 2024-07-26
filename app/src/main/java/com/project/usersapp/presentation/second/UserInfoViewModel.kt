package com.project.usersapp.presentation.second

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.usersapp.data.mapper.toUser
import com.project.usersapp.data.repository.UserRepository
import com.project.usersapp.domain.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user.asStateFlow()
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun getUserByEmail(email: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val userEntity = repository.getUserByEmail(email)
                if (userEntity != null) {
                    _user.value = userEntity.toUser()
                } else {
                    _error.value = "Пользователь не найден"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Неизвестная ошибка"
            } finally {
                _isLoading.value = false
            }
        }
    }
}