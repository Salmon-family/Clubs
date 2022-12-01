package com.thechance.identity.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

@Composable
fun BackButton(
    backButton: Int
) {
    Image(
        painter = painterResource(id = backButton),
        contentDescription = "back button"
    )
}