package com.devfalah.ui.screen.notification

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.R
import com.devfalah.ui.composable.CircleProfileImage
import com.devfalah.ui.composable.NotificationIcon
import com.devfalah.ui.spacer.VerticalSpacer16
import com.devfalah.ui.spacer.VerticalSpacer8
import com.devfalah.ui.theme.LightCardBackgroundColor
import com.devfalah.ui.theme.LightPrimaryBlackColor
import com.devfalah.ui.theme.LightTernaryBlackColor
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.viewmodels.Constants.LIKE_PHOTO
import com.devfalah.viewmodels.Constants.LIKE_POST
import com.devfalah.viewmodels.Constants.REQUEST_GROUP
import com.devfalah.viewmodels.notifications.NotificationState
import com.devfalah.viewmodels.notifications.NotificationsUIState
import com.devfalah.viewmodels.notifications.NotificationsViewModel

@Composable
fun NotificationScreen(
    navController: NavController,
    viewModel: NotificationsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    NotificationContent(state, viewModel::onNotificationClick)
}

@Composable
fun NotificationContent(
    state: NotificationsUIState,
    onNotificationClick: (NotificationState) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
    ) {
        items(state.notifications) {
            NotificationItem(notification = it, onNotificationClick)
        }
    }
}

@Composable
fun NotificationItem(
    notification: NotificationState,
    onNotificationClick: (NotificationState) -> Unit
) {
    val cardBackgroundColor = if (notification.viewed) {
        Color.Transparent
    } else {
        LightCardBackgroundColor
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onNotificationClick(notification) },
        backgroundColor = cardBackgroundColor,
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .fillMaxSize(),
        ) {

            CircleProfileImage(
                modifier = Modifier.align(CenterVertically),
                painter = rememberAsyncImagePainter(model = notification.posterPhoto),
                size = 56
            )
            VerticalSpacer16()

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                NotificationTitle(notification.type, notification.posterName)
                VerticalSpacer8()
                Text(
                    text = notification.timeCreated,
                    fontSize = 12.sp,
                    color = LightTernaryBlackColor,
                    lineHeight = 20.sp,
                    fontFamily = PlusJakartaSans
                )
            }
            if (!notification.viewed) {
                NotificationIcon()
                VerticalSpacer16()
            }
        }
    }
}

@Composable
private fun NotificationTitle(type: Int, posterName: String) {

    val notificationType = when (type) {
        LIKE_PHOTO -> stringResource(id = R.string.like_photo)
        LIKE_POST -> stringResource(id = R.string.like_post)
        REQUEST_GROUP -> stringResource(id = R.string.request_group)
        else -> ""
    }

    Text(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(fontWeight = FontWeight.Bold, fontFamily = PlusJakartaSans)
            ) {
                append(posterName)
            }
            withStyle(
                style = SpanStyle(fontWeight = FontWeight.Normal, fontFamily = PlusJakartaSans)
            ) {
                append(" $notificationType")
            }
        },
        modifier = Modifier.fillMaxWidth(), lineHeight = 20.sp, color = LightPrimaryBlackColor
    )
}
