package com.devfalah.ui.screen.postDetails.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.R
import com.devfalah.ui.composable.HeightSpacer8
import com.devfalah.ui.composable.WidthSpacer4
import com.devfalah.ui.screen.profile.composable.PostActionIcon
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.Typography
import com.devfalah.viewmodels.postDetails.CommentUIState

@Composable
fun CommentBody(
    comment: CommentUIState,
    onClickLikeComment: (CommentUIState) -> Unit,
    onValueChanged: (String) -> Unit,
    sendMessage: (CommentUIState) -> Unit,
    closeDialog: () -> Unit,
) {
    Column(modifier = Modifier
        .fillMaxWidth()) {
        if (comment.isEdited) {
            InputDialogView(onDismiss = {
                sendMessage(comment)
            },
                onExit = { closeDialog() },
                text = comment.content,
                onValueChanged = onValueChanged)
        } else {
            Text(
                text = comment.content,
                style = Typography.body2
            )
        }
        HeightSpacer8()
        Image(
            painter = rememberAsyncImagePainter(model = comment.imageFile),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        HeightSpacer8()
        Row {
            Row {
                PostActionIcon(
                    painter = painterResource(
                        id = if (comment.isLikedByUser) {
                            R.drawable.heart_full
                        } else {
                            R.drawable.like_icon
                        }
                    ),
                    tint = if (comment.isLikedByUser) {
                        LightPrimaryBrandColor
                    } else {
                        Color.Unspecified
                    },
                    onClick = { onClickLikeComment(comment) }
                )
                WidthSpacer4()
                Text(
                    text = if (comment.totalLikes > 0) {
                        "${comment.totalLikes}"
                    } else {
                        ""
                    }.take(3),
                    style = Typography.body2
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = comment.time,
                style = Typography.caption
            )
        }
    }
}