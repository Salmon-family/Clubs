package com.devfalah.ui.screen.postDetails.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devfalah.ui.theme.WhiteColor
import com.devfalah.viewmodels.postDetails.CommentUIState
import com.devfalah.viewmodels.postDetails.PostDetailsUIState

@Composable
fun CommentItem(
    modifier: Modifier = Modifier,
    state: CommentUIState,
    onClickLikeComment: (CommentUIState) -> Unit,
    onClickDeletedComment: (CommentUIState) -> Unit,
    onClickEditComment: (CommentUIState) -> Unit,
    onValueChanged: (String) -> Unit,
    sendMessage: (CommentUIState) -> Unit,
    closeDialog: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxWidth(),
        contentAlignment = if (state.isOwnerComment) Alignment.TopEnd else Alignment.TopStart) {
        Card(
            modifier = modifier
                .fillMaxWidth(.9f)
                .padding(horizontal = 16.dp),
            backgroundColor = MaterialTheme.colors.onSurface,
            shape = RoundedCornerShape(20.dp),
            elevation = 0.dp,
        ) {
            Column(modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                CommentHeader(state, onClickDeletedComment, onClickEditComment)
                CommentBody(
                    state,
                    onClickLikeComment,
                    onValueChanged,
                    sendMessage,
                    closeDialog,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CommentItemPreview() {
    CommentItem(state = CommentUIState(),
        onClickLikeComment = {},
        onClickDeletedComment = {},
        onClickEditComment = {},
        onValueChanged = {},
        sendMessage = {},
        closeDialog = {},
    )
}
