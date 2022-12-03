package com.thechance.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.thechance.viewmodels.chatWithFriend.uistates.ChatUiState

@Composable
fun FriendChat(
    chatUiState: ChatUiState,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(modifier = modifier.fillMaxWidth()) {
        val (image, user, recentMessage, time) = createRefs()
        PhotoFriendChat(chatUiState = chatUiState, Modifier
            .clip(CircleShape)
            .size(56.dp)
            .constrainAs(image) {
                top.linkTo(parent.top,12.dp)
                start.linkTo(parent.start, 12.dp)
                bottom.linkTo(parent.bottom, 12.dp)
            })

        UserChatTitle(chatUiState.fullName, modifier = Modifier.constrainAs(user) {
            top.linkTo(image.top, 3.dp)
            start.linkTo(image.end, 16.dp)
        })

        RecentMessage(chatUiState.recentMessage, Modifier.constrainAs(recentMessage) {
            bottom.linkTo(image.bottom, 3.dp)
            start.linkTo(image.end, 16.dp)
        })

        TimeRecentMessage(chatUiState.time, Modifier.constrainAs(time) {
            top.linkTo(parent.top, 16.dp)
            end.linkTo(parent.end, 21.dp)
        })
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
