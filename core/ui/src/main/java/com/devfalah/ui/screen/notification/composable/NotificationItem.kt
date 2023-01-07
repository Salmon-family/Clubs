package com.devfalah.ui.screen.notification.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.composable.CircleImage
import com.devfalah.ui.composable.HeightSpacer8
import com.devfalah.ui.composable.NotificationIcon
import com.devfalah.ui.composable.WidthSpacer16
import com.devfalah.ui.modifiers.nonRippleEffect
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.viewmodels.notifications.NotificationState

@Composable
fun NotificationItem(
    notification: NotificationState,
    onNotificationClick: (NotificationState) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colors.surface)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .nonRippleEffect { onNotificationClick(notification) },
        verticalAlignment = Alignment.CenterVertically,
    ) {

        CircleImage(
            painter = rememberAsyncImagePainter(model = notification.publisherImageUrl),
            size = 56
        )
        WidthSpacer16()

        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            NotificationTitle(notification.type, notification.publisherName)
            HeightSpacer8()
            Text(
                text = notification.timeCreated,
                fontSize = 12.sp,
                color = MaterialTheme.colors.onSecondary,
                lineHeight = 20.sp,
                fontFamily = PlusJakartaSans
            )
        }

        if (!notification.viewed) {
            NotificationIcon()
        }

    }
}

@Preview(showBackground = true)
@Composable
fun NotificationItemPreview() {
    NotificationItem(
        notification = NotificationState(
            id = 1,
            publisherId = 0,
            publisherName = "John Doe",
            timeCreated = "1 hour ago",
            type = 0,
            viewed = false,
            publisherImageUrl = "https://i.pravatar.cc/300"
        ),
        onNotificationClick = {}
    )
}
