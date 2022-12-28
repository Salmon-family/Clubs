package com.devfalah.ui.screen.menu.composable.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.devfalah.ui.R
import com.devfalah.ui.theme.LightBackgroundColor
import com.devfalah.ui.theme.Title

@Composable
fun ThemeBottomSheet(
    onChangeTheme: (Int) -> Unit
){
    val configuration = LocalConfiguration.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(
                (configuration.screenHeightDp * 0.3).dp,
                (configuration.screenHeightDp * 0.75).dp
            )
            .wrapContentHeight(unbounded = true)
    ) {
        Text(
            style = Title.h1,
            text = stringResource(id = R.string.choose_theme),
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp, start = 24.dp)
        )

        ThemeType(onChangeTheme = onChangeTheme)
    }
}