package com.thechance.identity.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun NavigateToAnotherScreen(
    hintText: Int,
    navigateText: Int,
    onNavigate: () -> Unit
) {
    Column {
        Spacer(Modifier.weight(1f))
        TextTwoToneColor(
            firstText = stringResource(id = hintText),
            secondText = stringResource(id = navigateText),
            navigate = onNavigate
        )
    }
}