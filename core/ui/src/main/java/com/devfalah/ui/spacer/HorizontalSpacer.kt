package com.devfalah.ui.spacer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalSpacer(
    height: Int = 16
) {
    Spacer(modifier = Modifier.height(height.dp))
}