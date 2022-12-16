package com.thechance.identity.ui.shape

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class ParallelogramShape(private val yValue: Float) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            Path().apply {
                lineTo(size.width, yValue)
                lineTo(size.width, size.height + yValue)
                lineTo(0f, size.height - (0.5 * yValue).toFloat())
                close()
            }
        )
    }
}
