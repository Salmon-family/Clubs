package com.thechance.identity.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

@Composable
fun BackButtonComposable(
    backButton: Int
) {
    Image(
        painter = painterResource(id = backButton),
        contentDescription = null
    )
}