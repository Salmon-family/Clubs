package com.devfalah.ui.modifiers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.RemoveRippleEffect(onClick: () -> Unit) = clickable(
    interactionSource = remember { MutableInteractionSource() },
    indication = rememberRipple(radius = 0.dp)
) {
    onClick()
}

