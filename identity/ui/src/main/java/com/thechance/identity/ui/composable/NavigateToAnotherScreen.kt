package com.thechance.identity.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.thechance.identity.ui.theme.LightPrimaryBrandColor
import com.thechance.identity.ui.theme.LightTernaryBlackColor

@Composable
fun NavigateToAnotherScreen(
    hintText: Int,
    navigateText: Int,
    onNavigate: () -> Unit
) {
    Column {
        Spacer(Modifier.weight(1f))
        TextTwoToneColor(
            text1 = stringResource(id = hintText),
            color1 = LightTernaryBlackColor,
            text2 = stringResource(id = navigateText),
            color2 = LightPrimaryBrandColor,
            navigate = onNavigate
        )
    }
}