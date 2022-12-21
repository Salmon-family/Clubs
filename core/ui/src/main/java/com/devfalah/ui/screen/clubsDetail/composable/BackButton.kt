package com.devfalah.ui.screen.clubsDetail.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.devfalah.ui.R
import com.devfalah.ui.theme.LightPrimaryBlackColor

@Composable
fun BackButton(
    modifier: Modifier,
    tint: Color = LightPrimaryBlackColor
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_back_arrow),
            contentDescription = null,
            tint = tint
        )
    }
}