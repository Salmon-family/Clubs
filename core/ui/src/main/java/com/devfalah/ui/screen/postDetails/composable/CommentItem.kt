package com.devfalah.ui.screen.postDetails.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devfalah.ui.theme.WhiteColor
import com.devfalah.viewmodels.postDetails.CommentUIState
import com.devfalah.viewmodels.postDetails.PostDetailsUIState

@Composable
fun CommentItem(
    modifier: Modifier = Modifier,
    state: CommentUIState,
    post: PostDetailsUIState,
    onClickLikeComment: (CommentUIState) -> Unit,
    onClickDeletedComment: (CommentUIState) -> Unit,
    onClickEditComment: (CommentUIState) -> Unit,
    onValueChanged: (String) -> Unit,
    sendMessage: (CommentUIState) -> Unit,
) {
    Row {
        if(state.ownerCommentId == post.postDetails.publisherId){
            Spacer(modifier = Modifier.weight(.04f))
        }
        Card(
            modifier = modifier
                .weight(.86f)
                .padding(horizontal = 16.dp),
            backgroundColor = WhiteColor,
            shape = RoundedCornerShape(20.dp),
            elevation = 0.dp,
        ) {
            Column(modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                CommentHeader(state, post, onClickDeletedComment, onClickEditComment)
                CommentBody(
                    state,
                    onClickLikeComment, onValueChanged, sendMessage
                )
            }
        }
        if(state.ownerCommentId != post.postDetails.publisherId){
            Spacer(modifier = Modifier.weight(.04f))
        }
    }
}
