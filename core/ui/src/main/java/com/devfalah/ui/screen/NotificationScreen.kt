package com.devfalah.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devfalah.ui.R
import com.devfalah.ui.theme.*
import com.devfalah.viewmodels.notifications.NotificationState
import com.devfalah.viewmodels.notifications.NotificationsUIState
import com.devfalah.viewmodels.notifications.NotificationsViewModel


@Composable
fun NotificationScreen(
    viewModel: NotificationsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    NotificationContent(state)
}

@Composable
fun NotificationContent(state: NotificationsUIState) {
    NotificationItem(notification = state.Notifications[0])
}

@Composable
fun NotificationItem(notification: NotificationState){
    Card(modifier = Modifier.fillMaxWidth(),
        backgroundColor = LightCardBackgroundColor,
    ) {
        Row(modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(modifier = Modifier
                .size(56.dp)
                .clip(CircleShape),
                painter = painterResource(id = R.drawable.user_image),
                contentDescription = "user",
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "notification.poster"+"commented on your post: " + "“This is just a testing text”."
                    , color = LightPrimaryBlackColor, lineHeight = 20.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "state", Modifier.height(20.dp)
                    , color = LightTernaryBlackColor,  lineHeight = 20.sp)
            }
        }
    }
}


@Preview()
@Composable
fun DefaultPreview() {
    NotificationScreen()
}