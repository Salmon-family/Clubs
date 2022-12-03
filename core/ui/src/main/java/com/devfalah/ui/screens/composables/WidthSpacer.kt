package com.devfalah.ui.screens.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WidthSpacer(width: Int) {
    Spacer(
        modifier = Modifier
            .fillMaxHeight()
            .width(width = width.dp)
    )
}

