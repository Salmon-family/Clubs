package com.devfalah.ui.screen.profile.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devfalah.ui.R
import com.devfalah.ui.modifiers.nonRippleEffect
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.LightTernaryBlackColor
import com.devfalah.ui.theme.PlusJakartaSans
import com.devfalah.ui.theme.WhiteColor

@Composable
fun PostCreatingSection(
    modifier: Modifier = Modifier,
    isMyProfile: Boolean = false,
    onCreatePost: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .nonRippleEffect { onCreatePost() }
            .fillMaxWidth()
            .clip(CircleShape)
            .background(WhiteColor)
            .requiredHeight(40.dp),
    ) {
        IconIndicator(
            modifier = Modifier.align(Alignment.CenterStart),
            painter = painterResource(
                if (isMyProfile) {
                    R.drawable.pen
                } else {
                    R.drawable.ic_message
                }
            )
        )
        Text(
            text = stringResource(
                id = if (isMyProfile) {
                    R.string.create_post
                } else {
                    R.string.send_message
                }
            ),
            color = LightTernaryBlackColor,
            fontSize = 14.sp,
            maxLines = 1,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Start,
            fontFamily = PlusJakartaSans,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 56.dp)
        )
    }
}

@Composable
fun IconIndicator(
    modifier: Modifier = Modifier,
    painter: Painter
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxHeight()
            .padding(2.dp)
            .clip(CircleShape)
            .aspectRatio(
                ratio = 1.0F,
                matchHeightConstraintsFirst = true,
            )
            .background(LightPrimaryBrandColor),
    ) {
        Icon(
            painter = painter,
            tint = Color.Unspecified,
            contentDescription = null,
        )
    }
}