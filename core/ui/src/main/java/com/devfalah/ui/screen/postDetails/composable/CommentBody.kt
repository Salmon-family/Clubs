package com.devfalah.ui.screen.postDetails.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.devfalah.ui.R
import com.devfalah.ui.composable.HeightSpacer8
import com.devfalah.ui.composable.WidthSpacer4
import com.devfalah.ui.screen.profile.composable.PostActionIcon
import com.devfalah.ui.theme.AppTypography
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.viewmodels.postDetails.CommentUIState

@Composable
fun CommentBody(
    state: CommentUIState,
    onClickLikeComment: (CommentUIState) -> Unit,
    onValueChanged: (String) -> Unit,
    sendMessage: (CommentUIState) -> Unit,
    closeDialog: () -> Unit,
) {
    Column(modifier = Modifier
        .fillMaxWidth()) {
        if (state.isEdited) {
            InputDialogView(onDismiss = {
                sendMessage(state)
            },
                onExit = { closeDialog() },
                text = state.content,
                onValueChanged = onValueChanged,
                state = state
            )
        } else {
            Text(
                text = state.content,
                style = AppTypography.body2,
                color = MaterialTheme.colors.secondary,
            )
        }
        HeightSpacer8()
        Row {
            Row {
                PostActionIcon(
                    painter = painterResource(
                        id = if (state.isLikedByUser) {
                            R.drawable.heart_full
                        } else {
                            R.drawable.like_icon
                        }
                    ),
                    tint = if (state.isLikedByUser) {
                        LightPrimaryBrandColor
                    } else {
                        Color.Unspecified
                    },
                    onClick = { onClickLikeComment(state) }
                )
                WidthSpacer4()
                Text(
                    text = if (state.totalLikes > 0) {
                        "${state.totalLikes}"
                    } else {
                        ""
                    }.take(3),
                    style = AppTypography.body2
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = state.time,
                style = AppTypography.caption,
                color = MaterialTheme.colors.secondaryVariant,
                )
        }
    }
}