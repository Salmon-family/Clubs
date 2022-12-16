package com.devfalah.ui.screen.menu.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devfalah.ui.R
import com.devfalah.ui.composable.HeightSpacer16
import com.devfalah.ui.theme.LightBackgroundColor
import com.devfalah.ui.theme.LightPrimaryBrandColor

@Composable
fun TopSection(
    modifier: Modifier = Modifier,
    onClickProfile: () -> Unit,
    onClickSavedThreads: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        MenuItem(
            text = stringResource(R.string.my_account),
            painter = painterResource(R.drawable.ic_menu_avatar),
            onClickItem = onClickProfile,
            backgroundColor = LightPrimaryBrandColor,
            textColor = LightBackgroundColor,
            iconSize = 32.dp,
            iconColor = Color.Unspecified,
            iconModifier = Modifier.border(
                BorderStroke(2.dp, LightBackgroundColor),
                CircleShape
            )
        )

        HeightSpacer16()

        MenuItem(
            text = stringResource(R.string.saved_threads),
            painter = painterResource(R.drawable.ic_menu_saved),
            onClickItem = onClickSavedThreads
        )
    }
}

@Preview
@Composable
fun PreviewTopSection() {
    TopSection(
        onClickProfile = {},
        onClickSavedThreads = {}
    )
}