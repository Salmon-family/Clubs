package com.devfalah.ui.modifiers

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.LayoutDirection
import androidx.core.text.layoutDirection
import java.util.*

fun Modifier.flipWithLanguage(): Modifier {
    val isLayoutRtl = Locale.getDefault().layoutDirection == LayoutDirection.Rtl.ordinal
    return if (isLayoutRtl) this.scale(scaleX = -1f, scaleY = 1f) else this
}