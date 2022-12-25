package com.devfalah.ui.screen.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devfalah.ui.composable.setStatusBarColor
import com.devfalah.ui.Screen
import com.devfalah.ui.composable.AppBar
import com.devfalah.ui.screen.notification.composable.NotificationItem
import com.devfalah.ui.theme.LightBackgroundColor
import com.devfalah.viewmodels.notifications.NotificationState
import com.devfalah.viewmodels.notifications.NotificationsUIState
import com.devfalah.viewmodels.notifications.NotificationsViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun NotificationScreen(
    navController: NavController,
    viewModel: NotificationsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val systemUIController = rememberSystemUiController()

    NotificationContent(navController, state, viewModel::onNotificationClick)
    LaunchedEffect(true) {
        setStatusBarColor(
            systemUIController = systemUIController,
            color = LightBackgroundColor,
            darkIcons = true
        )
    }
}

@Composable
fun NotificationContent(
    navController: NavController,
    state: NotificationsUIState,
    onNotificationClick: (NotificationState) -> Unit
) {
    Column {
        AppBar(title = stringResource(id = Screen.Notification.title), navHostController =navController )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = LightBackgroundColor),
            contentPadding = PaddingValues(vertical = 16.dp),
        ) {
            items(state.notifications) {
                NotificationItem(notification = it, onNotificationClick)
            }
        }
    }
}

