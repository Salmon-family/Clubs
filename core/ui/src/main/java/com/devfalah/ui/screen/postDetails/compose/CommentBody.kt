package com.devfalah.ui.screen.postDetails.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.devfalah.viewmodels.postDetails.CommentUIState

@Composable
fun CommentBody(
    modifier: Modifier = Modifier,
    state: CommentUIState,
) {

    Column(modifier = modifier) {
        Text(text = state.text)

        Row(modifier = Modifier.fillMaxWidth()) {

        }
    }

}