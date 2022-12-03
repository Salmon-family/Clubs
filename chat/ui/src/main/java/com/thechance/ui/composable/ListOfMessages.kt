package com.thechance.ui.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import com.thechance.ui.components.ReceiverMessage
import com.thechance.ui.components.SenderMessage
import com.thechance.ui.spacer.SpaceVertical
import com.thechance.ui.utilities.convertIntToTime
import com.thechance.viewmodels.chatWithFriend.states.ChatUIState
import com.thechance.viewmodels.chatWithFriend.states.MessageUIState

@Composable
fun ListOfChat(
    state: List<MessageUIState>,
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (listOfChat) = createRefs()
        LazyColumn(modifier = Modifier.constrainAs(listOfChat) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, reverseLayout = false) {
            items(state) {

                // TODO: when get user id from navigation component

//                if (it.friendId) {
                    SenderMessage(it.message, it.messageDate.convertIntToTime())
//                } else {
//                    ReceiverMessage(it.message, it.messageDate.convertIntToTime())
//                }
            }
            item {
                SpaceVertical(height = 100)
            }
        }
    }
}