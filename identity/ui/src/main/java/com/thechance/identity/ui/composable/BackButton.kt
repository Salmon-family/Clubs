package com.thechance.identity.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.core.text.layoutDirection
import com.thechance.identity.ui.R
import java.util.*

@Composable
fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true
) {
    Column(
        modifier = modifier.wrapContentSize(),
        horizontalAlignment = Alignment.Start
    ) {
        IconButton(onClick = onClick, enabled = isEnabled) {
            Icon(
                modifier = Modifier.flipWithLanguage(),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_back_arrow),
                contentDescription = null,
                tint = MaterialTheme.colors.primaryVariant,
            )
        }
    }
}

fun Modifier.flipWithLanguage(): Modifier {
    val isLayoutRtl = Locale.getDefault().layoutDirection == LayoutDirection.Rtl.ordinal
    return if (isLayoutRtl) this.scale(scaleX = -1f, scaleY = 1f) else this
}