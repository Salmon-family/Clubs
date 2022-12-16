package com.thechance.ui.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SpaceHorizontal(width: Int) {
    Spacer(modifier = Modifier.width(width.dp))
}