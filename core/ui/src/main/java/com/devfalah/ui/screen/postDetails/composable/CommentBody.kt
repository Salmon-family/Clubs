package com.devfalah.ui.screen.postDetails.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.devfalah.ui.R
import com.devfalah.ui.composable.HeightSpacer8
import com.devfalah.ui.composable.WidthSpacer4
import com.devfalah.ui.screen.profile.composable.PostActionIcon
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.Typography
import com.devfalah.viewmodels.postDetails.CommentUIState
import com.devfalah.viewmodels.userProfile.PostUIState

@Composable
fun CommentBody(
    comment: CommentUIState,
    onClickLike: (CommentUIState) -> Unit,
    ) {
    Column(modifier = Modifier
        .fillMaxWidth()) {
        Text(
            text = comment.content,
            style = Typography.body2
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
                    onClick = { /*onClickLike(comment)*/ }
                )
                WidthSpacer4()
                Text(
                    text = comment.totalLikes.toString(),
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

