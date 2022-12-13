package com.devfalah.ui.screen.postDetails.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devfalah.ui.theme.WhiteColor
import com.devfalah.viewmodels.postDetails.CommentUIState

@Composable
fun CommentItem(
    modifier: Modifier = Modifier,
    state: CommentUIState,
    onClickLike: (CommentUIState) -> Unit,
//    onClickDeletedComment: (CommentUIState) -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        backgroundColor = WhiteColor,
        shape = RoundedCornerShape(20.dp)
    ){
        Column(modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            CommentHeader(state, /*onClickDeletedComment*/)
            CommentBody(state, onClickLike)
        }
    }
}
