package com.devfalah.ui.screen.postDetails.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.devfalah.ui.R
import com.devfalah.ui.composable.ExpandableText
import com.devfalah.ui.composable.HeightSpacer8
import com.devfalah.ui.composable.WidthSpacer4
import com.devfalah.ui.modifiers.nonRippleEffect
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.LightSecondaryBlackColor
import com.devfalah.ui.theme.LightSecondaryGrayColor
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.ui.util.getDataDescription
import com.devfalah.viewmodels.postDetails.CommentUIState

@Composable
fun CommentBody(
    modifier: Modifier = Modifier,
    state: CommentUIState,
    onClickLike: () -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        ExpandableText(
            modifier = Modifier.fillMaxWidth(),
            text = state.text,
            minimizedMaxLines = 2,
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colors.onSurface,
                textAlign = if (state.isOwnerComment) {
                    TextAlign.End
                } else {
                    TextAlign.Start
                }
            ),
            isTextAlignToStart = state.isOwnerComment
        )

        HeightSpacer8()

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "${state.timeCreate.value} ${getDataDescription(state.timeCreate.description)} ",
                fontSize = 12.sp,
                color = MaterialTheme.colors.secondaryVariant,
                fontWeight = FontWeight.Normal
            )

            LikeCommentIcon(
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
                    MaterialTheme.colors.secondaryVariant
                },
                onClick = onClickLike,
                totalLikes = state.totalLikes
            )
        }
    }

}


@Composable
fun LikeCommentIcon(
    onClick: () -> Unit,
    painter: Painter,
    tint: Color = Color.Unspecified,
    totalLikes: Int,
) {
    Row {

        if (totalLikes > 0) {
            Text(
                text = "$totalLikes",
                fontSize = 12.sp,
                color = LightSecondaryGrayColor,
                fontWeight = FontWeight.Normal
            )
            WidthSpacer4()
        }

        Icon(
            modifier = Modifier.nonRippleEffect { onClick() },
            painter = painter,
            tint = tint,
            contentDescription = null
        )
    }
}

