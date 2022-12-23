package com.devfalah.ui.screen.postDetails.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.devfalah.ui.R
import com.devfalah.ui.composable.CircleImage
import com.devfalah.ui.composable.HeightSpacer4
import com.devfalah.ui.theme.LightPrimaryBlackColor
import com.devfalah.viewmodels.postDetails.CommentUIState

@Composable
fun CommentHeader(
    modifier: Modifier = Modifier,
    state: CommentUIState
) {
    Row(modifier = modifier.fillMaxWidth()) {
        CircleImage(
            painter = rememberAsyncImagePainter(
                model = state.userPictureUrl,
                error = rememberAsyncImagePainter(model = R.drawable.test_image)
            ),
            size = 32
        )

        HeightSpacer4()

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