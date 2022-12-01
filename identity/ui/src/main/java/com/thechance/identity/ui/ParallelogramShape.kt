package com.thechance.identity.ui

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class ParallelogramShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            Path().apply {
                lineTo(size.width, 72f)
                lineTo(size.width, size.height + 72f)
                lineTo(0f, size.height - 36f)
                close()
            }
        )
    }
}
//moveTo(0f, 103f)
//lineTo(size.width, 176f)
//lineTo(size.width, 467f)
//lineTo(0f, 407f)
//lineTo(0f, 103f)