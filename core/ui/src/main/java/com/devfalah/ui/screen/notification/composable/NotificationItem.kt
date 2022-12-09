package com.devfalah.ui.screen.notification.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.composable.CircleProfileImage
import com.devfalah.ui.composable.NotificationIcon
import com.devfalah.ui.composable.HeightSpacer16
import com.devfalah.ui.composable.HeightSpacer8
import com.devfalah.ui.composable.WidthSpacer16
import com.devfalah.ui.modifiers.RemoveRippleEffect
import com.devfalah.ui.theme.LightCardBackgroundColor
import com.devfalah.ui.theme.LightTernaryBlackColor
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.viewmodels.notifications.NotificationState

@Composable
fun NotificationItem(
    notification: NotificationState,
    onNotificationClick: (NotificationState) -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .RemoveRippleEffect { onNotificationClick(notification) },
            backgroundColor = LightCardBackgroundColor,
            elevation = 0.dp,
            shape = RoundedCornerShape(20.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .fillMaxSize(),
            ) {

                CircleProfileImage(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    painter = rememberAsyncImagePainter(model = notification.posterPhoto),
                    size = 56
                )
                WidthSpacer16()

                Column(
                    modifier = Modifier.weight(1f).fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                    NotificationTitle(notification.type, notification.posterName)
                    HeightSpacer8()
                    Text(
                        text = notification.timeCreated,
                        fontSize = 12.sp,
                        color = LightTernaryBlackColor,
                        lineHeight = 20.sp,
                        fontFamily = PlusJakartaSans
                    )
                }
            }
        }
        if (!notification.viewed) {
            NotificationIcon(Modifier.align(Alignment.TopEnd).padding(top = 2.dp, end = 30.dp))
        }
    }
}

@Preview (showBackground = true)
@Composable
fun NotificationItemPreview() {
    NotificationItem(
        notification = NotificationState(
            id = 1,
            posterID = 0,
            posterName = "John Doe",
            timeCreated = "1 hour ago",
            type = 0,
            viewed = false,
            posterPhoto = "https://i.pravatar.cc/300"
        ),
        onNotificationClick = {}
    )
}