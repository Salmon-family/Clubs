package com.thechance.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.thechance.ui.toTime
import com.thechance.viewmodels.chats.uiStates.ChatUiState



@Composable
fun FriendChat(
    chatUiState: ChatUiState,
    onClick: (ChatUiState) -> Unit,
    modifier: Modifier = Modifier,

    ) {
    Card(
        backgroundColor = Color.White,
        elevation = 0.dp,
        modifier = modifier
            .fillMaxWidth().clip(RoundedCornerShape(20.dp),)
            .clickable {
                onClick(chatUiState)
            },
    ) {
        ConstraintLayout(

        ) {
            val configuration = LocalConfiguration.current
            val screenWidth = configuration.screenWidthDp
            val (image, user, recentMessage, time) = createRefs()
            PhotoFriendChat(
                url = chatUiState.icon,
                modifier = Modifier.constrainAs(image) {
                    top.linkTo(parent.top, 12.dp)
                    start.linkTo(parent.start, 12.dp)
                    bottom.linkTo(parent.bottom, 12.dp)
                },
            )
            UserChatTitle(
                chatUiState.fullName,
                modifier = Modifier
                    .width((screenWidth * .44).dp)
                    .constrainAs(user) {
                        top.linkTo(image.top, 3.dp)
                        start.linkTo(image.end, 16.dp)
                    },
            )
            RecentMessage(
                message = chatUiState.recentMessage,
                modifier = Modifier
                    .width((screenWidth * .7).dp)
                    .constrainAs(recentMessage) {
                        bottom.linkTo(image.bottom, 3.dp)
                        start.linkTo(image.end, 16.dp)
                    },
            )
            TimeRecentMessage(
                chatUiState.time.toTime(),
                Modifier.constrainAs(time) {
                    top.linkTo(parent.top, 16.dp)
                    end.linkTo(parent.end, 21.dp)
                },
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewUserChat() {
    FriendChat(
        chatUiState = ChatUiState(
            fullName = "Falah Hassan",
            icon = "",
            time = 121231313,
            recentMessage = "Hello, Good morning✨"
        ),
        onClick = {}
    )
}
