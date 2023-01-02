package com.devfalah.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun EmptyScreenIcon(
    modifier: Modifier = Modifier,
    painter: Painter
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(MaterialTheme.colors.secondary)
            .padding(24.dp)
    ) {
        Icon(
            modifier = Modifier.size(56.dp),
            painter = painter,
            tint = MaterialTheme.colors.primary,
            contentDescription = null
        )
    }
}