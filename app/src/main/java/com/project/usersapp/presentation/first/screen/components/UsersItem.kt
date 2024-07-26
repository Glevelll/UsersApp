package com.project.usersapp.presentation.first.screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.usersapp.domain.User

@Composable
fun UsersItem(user: User, onDeleteClick: (User) -> Unit, onItemClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .clickable { onItemClick(user.email) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Text(
                    text = user.firstName + " " + user.lastName,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = user.email,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Удалить",
                    modifier = Modifier
                        .padding(end = 6.dp)
                        .align(Alignment.CenterEnd)
                        .clickable { onDeleteClick(user) }
                )
            }
        }
    }
}