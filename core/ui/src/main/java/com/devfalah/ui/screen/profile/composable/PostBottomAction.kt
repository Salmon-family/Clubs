package com.devfalah.ui.screen.profile.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.devfalah.ui.R
import com.devfalah.ui.composable.HeightSpacer16
import com.devfalah.ui.composable.WidthSpacer16
import com.devfalah.ui.composable.WidthSpacer24
import com.devfalah.ui.composable.WidthSpacer8
import com.devfalah.ui.theme.LightPrimaryBlackColor
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.LightSecondaryBlackColor
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.viewmodels.userProfile.PostUIState

@Composable
fun PostBottomAction(
    post: PostUIState,
    onClickLike: (PostUIState) -> Unit,
    onClickComment: (PostUIState) -> Unit,
    onClickSave: (PostUIState) -> Unit
) {
    HeightSpacer16()

    Row(modifier = Modifier.fillMaxWidth()) {
        WidthSpacer16()
        Icon(
            modifier = Modifier.clickable { onClickLike(post) },
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
                Color.Unspecified
            },
            contentDescription = null
        )
        WidthSpacer8()
        if (post.totalLikes > 0) {
            Text(
                text = "${post.totalLikes}",
                fontSize = 14.sp,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Normal,
                color = LightSecondaryBlackColor
            )
        }
        WidthSpacer24()

        Icon(
            modifier = Modifier.clickable { onClickComment(post) },
            painter = painterResource(id = R.drawable.comment_icon),
            contentDescription = null
        )

        WidthSpacer8()
        if (post.totalComments > 0) {
            Text(
                text = "${post.totalComments}",
                fontSize = 14.sp,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Normal,
                color = LightSecondaryBlackColor
            )
        }
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.CenterEnd
        ) {
            Image(
                modifier = Modifier
                    .clickable { onClickSave(post) },
                alignment = Alignment.CenterEnd,
                painter = painterResource(
                    id = if (post.isSaved) {
                        R.drawable.save_full
                    } else {
                        R.drawable.save_icon
                    }
                ),
                contentDescription = null,
            )
        }
        WidthSpacer16()
    }
}
