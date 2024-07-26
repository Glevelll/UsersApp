package com.project.usersapp.presentation.second.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.usersapp.presentation.second.UserInfoViewModel

@Composable
fun UserInfoScreen(viewModel: UserInfoViewModel, onClose: () -> Unit) {
    val isLoading by viewModel.isLoading.collectAsState()
    val user by viewModel.user.collectAsState()
    val error by viewModel.error.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(16.dp)) {
            if (isLoading) {
                CircularProgressIndicator()
            } else if (error != null) {
                Text("Ошибка: $error")
            } else if (user != null) {
                Spacer(modifier = Modifier.height(60.dp))
                Text(
                    text = "${user!!.firstName} ${user!!.lastName}",
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Email: ${user!!.email}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Пол: ${user!!.gender}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Город: ${user!!.city}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Страна: ${user!!.country}")
            }
        }

        IconButton(
            onClick = onClose,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
                .size(50.dp, 50.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Закрыть"
            )
        }
    }
}