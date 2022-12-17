package com.devfalah.ui.screen.menu.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.devfalah.ui.R
import com.devfalah.ui.composable.HeightSpacer8

@Composable
fun PreferencesSection(
    modifier: Modifier = Modifier,
    onClickTheme: () -> Unit,
    onClickLanguage: () -> Unit
) {
    MenuSection(
        modifier = modifier,
        sectionTitle = stringResource(R.string.preferences_section)
    ) {
        MenuItem(
            text = stringResource(R.string.theme),
            painter = painterResource(R.drawable.ic_menu_theme),
            onClickItem = onClickTheme
        )

        HeightSpacer8()

        MenuItem(
            text = stringResource(R.string.language),
            painter = painterResource(R.drawable.ic_menu_language),
            onClickItem = onClickLanguage
        )
    }
}

@Preview
@Composable
fun PreviewPreferencesSection() {
    PreferencesSection(
        onClickTheme = {},
        onClickLanguage = {}
    )
}