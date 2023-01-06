package com.devfalah.ui.screen.profile.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devfalah.ui.R
import com.devfalah.ui.composable.HeightSpacer16
import com.devfalah.ui.composable.WidthSpacer16
import com.devfalah.ui.composable.WidthSpacer4
import com.devfalah.ui.composable.WidthSpacer8
import com.devfalah.ui.modifiers.nonRippleEffect
import com.devfalah.ui.screen.clubCreation.showToastMessage
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.viewmodels.userProfile.PostUIState

@Composable
fun PostBottomAction(
    post: PostUIState,
    onClickLike: (PostUIState) -> Unit,
    onClickComment: (PostUIState) -> Unit,
    onClickSave: (PostUIState) -> Unit
) {
    val context = LocalContext.current

    HeightSpacer16()

    Row(modifier = Modifier.fillMaxWidth()) {
        WidthSpacer16()

        PostActionIcon(
            painter = painterResource(
                id = if (post.isLikedByUser) {
                    R.drawable.heart_full
                } else {
                    R.drawable.like_icon
                }
            ),
            tint = if (post.isLikedByUser) {
                LightPrimaryBrandColor
            } else {
                MaterialTheme.colors.secondaryVariant
            },
            onClick = { onClickLike(post) }
        )

        WidthSpacer8()

        PostTextValue(
            if (post.totalLikes > 0) {
                "${post.totalLikes}"
            } else {
                ""
            }.take(3)
        )

        WidthSpacer16()

        PostActionIcon(
            painter = painterResource(id = R.drawable.ic_comment),
            onClick = { onClickComment(post) }
        )

        WidthSpacer4()

        PostTextValue(
            if (post.totalComments > 0) {
                "${post.totalComments}"
            } else {
                ""
            }
        )

        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterEnd) {
            PostActionIcon(
                painter = painterResource(
                    id = if (post.isSaved) {
                        R.drawable.save_full
                    } else {
                        R.drawable.save_icon
                    }
                ),
                onClick = {
                    onClickSave(post)
                    showToastMessage(
                        context,
                        if (post.isSaved) {
                            context.getString(R.string.thread_has_been_saved)
                        } else {
                            context.getString(R.string.thread_has_been_unsaved)
                        }
                    )
                }
            )
        }
        WidthSpacer16()
    }
}

@Composable
private fun PostTextValue(text: String) {
    Text(
        modifier = Modifier.sizeIn(minWidth = 24.dp, maxWidth = 24.dp),
        text = text,
        fontSize = 14.sp,
        fontFamily = PlusJakartaSans,
        fontWeight = FontWeight.Normal,
        color = MaterialTheme.colors.secondaryVariant
    )
}


@Composable
fun PostActionIcon(
    onClick: () -> Unit,
    painter: Painter,
    tint: Color = MaterialTheme.colors.secondaryVariant
) {
    Icon(
        modifier = Modifier.nonRippleEffect { onClick() },
        painter = painter,
        tint = tint,
        contentDescription = null
    )
}