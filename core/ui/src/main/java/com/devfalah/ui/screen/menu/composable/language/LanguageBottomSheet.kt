package com.devfalah.ui.screen.menu.composable.language

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.devfalah.ui.theme.Title
import com.devfalah.ui.R
import com.devfalah.ui.composable.SegmentControls
import com.devfalah.ui.theme.LightBackgroundColor

@Composable
fun LanguageBottomSheet(
    onChangeLanguage: (Int) -> Unit
){
    val configuration = LocalConfiguration.current

    Column(
        modifier = Modifier.fillMaxWidth()
            .heightIn(
                (configuration.screenHeightDp * 0.25).dp,
                (configuration.screenHeightDp * 0.75).dp
            )
            .wrapContentHeight(unbounded = true)
    ) {
        Text(
            style = Title.h1,
            text = stringResource(id = R.string.app_language),
            color = Color.Black,
            modifier = Modifier.padding(start = 16.dp, bottom = 24.dp)
        )

        SegmentControls(
            items = listOf(
                stringResource(id = R.string.english),
                stringResource(id = R.string.arabic)
            ),
            onItemSelection = onChangeLanguage,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
    }
}