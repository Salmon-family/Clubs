package com.devfalah.ui.screen.postDetails.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devfalah.ui.screen.postDetails.compose.CommentBody
import com.devfalah.ui.screen.postDetails.compose.CommentHeader
import com.devfalah.ui.theme.LightCardBackgroundColor
import com.devfalah.viewmodels.postDetails.CommentUIState

@Composable
fun CommentItem(
    modifier: Modifier = Modifier,
    state: CommentUIState
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = if (state.isOwnerComment) Alignment.TopEnd else Alignment.TopStart
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth(.9f)
                .padding(horizontal = 16.dp),
            backgroundColor = LightCardBackgroundColor,
            shape = RoundedCornerShape(20.dp),
            elevation = 0.dp,
        ) {
            Column(modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                CommentHeader(state = state)
                CommentBody(state = state)
            }
        }
    }
}


