package com.devfalah.ui.screen.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.Screen
import com.devfalah.ui.composable.*
import com.devfalah.ui.screen.clubRequests.navigateToClubRequests
import com.devfalah.ui.screen.notification.composable.EmptyNotificationsItem
import com.devfalah.ui.screen.notification.composable.NotificationItem
import com.devfalah.ui.screen.postDetails.navigateToPostDetails
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.util.observeAsState
import com.devfalah.viewmodels.notifications.NotificationState
import com.devfalah.viewmodels.notifications.NotificationsUIState
import com.devfalah.viewmodels.notifications.NotificationsViewModel
import com.devfalah.viewmodels.util.Constants
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun NotificationScreen(
    navController: NavController,
    viewModel: NotificationsViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    val systemUIController = rememberSystemUiController()
    val lifecycleState = LocalLifecycleOwner.current.lifecycle.observeAsState()
    LaunchedEffect(key1 = lifecycleState.value) {
        if (lifecycleState.value == Lifecycle.Event.ON_RESUME) {
            viewModel.refreshNotification()
        }
    }
    NotificationContent(
        state = state,
        onClickTryAgain = viewModel::getUserNotifications,
        onRetry = viewModel::getUserNotifications,
        onRefresh = viewModel::refreshNotification,
    ) {
        viewModel.markNotificationAsViewed(it)

        when (it.type) {
            Constants.REQUEST_GROUP ->
                navController.navigateToClubRequests(it.subjectId, it.id)
            Constants.LIKE_POST,
            Constants.COMMENT_POST,
            Constants.LIKE_COMMENT_POST, ->
                navController.navigateToPostDetails(id = it.subjectId)
        }
    }

    val color = MaterialTheme.colors.background
    LaunchedEffect(true) {
        setStatusBarColor(
            systemUIController = systemUIController,
            color = color,
        )
    }
}

@Composable
fun NotificationContent(
    state: NotificationsUIState,
    onClickTryAgain: () -> Unit,
    onRetry: () -> Unit,
    onRefresh: () -> Unit,
    onNotificationClick: (NotificationState) -> Unit,
) {
    Column {
        AppBar(
            title = stringResource(id = Screen.Notification.title),
            showBackButton = false,
        )
        if (state.isLoading) {
            Loading()
        } else if (state.error.isNotBlank()) {
            ErrorItem(onClickRetry = onRetry)
        } else if (state.notifications.isEmpty()) {
            EmptyNotificationsItem()
        }
        ManualPager(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background),
            onRefresh = onRefresh,
            isLoading = state.isPagerLoading,
            error = state.pagerError,
            isEndOfPager = state.isEndOfPager,
            contentPadding = PaddingValues(16.dp),
        ) {
            item(key = "notifications") {
                NotificationsStatusItem(
                    state = state,
                    onClickTryAgain = onClickTryAgain
                )
            }

            items(state.notifications) {
                NotificationItem(
                    notification = it,
                    onNotificationClick
                )
            }
        }
    }
}

@Composable
private fun NotificationsStatusItem(
    modifier: Modifier = Modifier,
    state: NotificationsUIState,
    onClickTryAgain: () -> Unit,
) {
    Row(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = LightPrimaryBrandColor)
            }
        } else if (state.error.isNotEmpty()) {
            Text(modifier = Modifier.weight(1f), text = state.error)
            Button(onClick = { onClickTryAgain() }) {
                Text(text = stringResource(id = R.string.try_again))
            }
        }
    }
}

