package com.project.usersapp.presentation.second

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.project.usersapp.presentation.second.screen.UserInfoScreen
import com.project.usersapp.presentation.theme.UsersAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserInfoActivity : ComponentActivity() {
    private val viewModel: UserInfoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val email = intent.getStringExtra("USER_EMAIL") ?: return
        setContent {
            UsersAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UserInfoScreen(viewModel) {
                        finish()
                    }
                }
            }
        }
        viewModel.getUserByEmail(email)
    }
}
