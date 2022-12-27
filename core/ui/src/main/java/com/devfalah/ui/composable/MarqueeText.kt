package com.devfalah.ui.composable

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun MarqueeText(
    title: String,
    durationMillis: Int = 1000,
    delay: Int = 100
) {
    val scrollState = rememberScrollState()
    var shouldAnimated by remember {
        mutableStateOf(true)
    }
    LaunchedEffect(key1 = shouldAnimated) {
        scrollState.animateScrollTo(
            scrollState.maxValue,
            animationSpec = tween(
                durationMillis, delay, easing = CubicBezierEasing(0f, 0f, 0f, 0f)
            )
        )
        scrollState.scrollTo(0)
        shouldAnimated = !shouldAnimated
    }

    Text(
        text = title,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.horizontalScroll(scrollState, false)
    )
}