package com.thechance.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.thechance.viewmodels.chatWithFriend.chatWithFriend.uiState.ChatUiState

@Composable
fun FriendChat(
    chatUiState: ChatUiState,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier.fillMaxWidth(),
    ) {
        val configuration = LocalConfiguration.current
        val screenWidth = configuration.screenWidthDp
        val (image, user, recentMessage, time) = createRefs()
        PhotoFriendChat(
            url = chatUiState.icon,
            modifier = Modifier.constrainAs(image) {
                top.linkTo(parent.top,12.dp)
                start.linkTo(parent.start, 12.dp)
                bottom.linkTo(parent.bottom, 12.dp)
            },
        )
        UserChatTitle(
            chatUiState.fullName,
            modifier = Modifier
                .width((screenWidth*.45).dp)
                .constrainAs(user) {
                    top.linkTo(image.top, 3.dp)
                    start.linkTo(image.end, 16.dp)
                },
        )
        RecentMessage(
            message = chatUiState.recentMessage,
            modifier = Modifier
                .width((screenWidth*.6).dp)
                .constrainAs(recentMessage) {
                    bottom.linkTo(image.bottom, 3.dp)
                    start.linkTo(image.end, 16.dp)
                },
        )
        TimeRecentMessage(
            chatUiState.time,
            Modifier.constrainAs(time) {
                top.linkTo(parent.top, 16.dp)
                end.linkTo(parent.end, 21.dp)
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserChat() {
    FriendChat(
        chatUiState = ChatUiState(
            fullName = "Ahmed",
        )
    )
}
