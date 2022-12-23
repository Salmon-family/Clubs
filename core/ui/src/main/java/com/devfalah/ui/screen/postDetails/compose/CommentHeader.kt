package com.devfalah.ui.screen.postDetails.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.R
import com.devfalah.ui.composable.CircleImage
import com.devfalah.ui.composable.WidthSpacer8
import com.devfalah.ui.theme.LightPrimaryBlackColor
import com.devfalah.viewmodels.postDetails.CommentUIState

@Composable
fun CommentHeader(
    modifier: Modifier = Modifier,
    state: CommentUIState
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (state.isOwnerComment) {

            Text(
                modifier = modifier.weight(1f),
                text = state.userName,
                textAlign = TextAlign.End,
                fontSize = 14.sp,
                color = LightPrimaryBlackColor,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )

            WidthSpacer8()

            CircleImage(
                painter = rememberAsyncImagePainter(
                    model = state.userPictureUrl,
                    error = rememberAsyncImagePainter(model = R.drawable.test_image)
                ),
                size = 32
            )

        } else {

            CircleImage(
                painter = rememberAsyncImagePainter(
                    model = state.userPictureUrl,
                    error = rememberAsyncImagePainter(model = R.drawable.test_image)
                ),
                size = 32
            )

            WidthSpacer8()

            Text(
                text = state.userName,
                textAlign = TextAlign.Start,
                fontSize = 14.sp,
                color = LightPrimaryBlackColor,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )
        }

    }
}