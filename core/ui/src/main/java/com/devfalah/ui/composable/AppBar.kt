package com.devfalah.ui.composable

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.layoutDirection
import com.devfalah.ui.R
import com.devfalah.ui.theme.PlusJakartaSans
import java.util.*

@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    title: String,
    onBackButton: () -> Unit = {},
    showBackButton: Boolean = true,
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = MaterialTheme.colors.background,
    contentColor: Color = MaterialTheme.colors.primaryVariant,
    elevation: Dp = 0.dp
) {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier.offset(x = if (showBackButton) (-12).dp else 0.dp),
                text = title,
                style = TextStyle(
                    color = MaterialTheme.colors.primaryVariant,
                    fontFamily = PlusJakartaSans,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        },
        navigationIcon = if (showBackButton) {
            {
                IconButton(onClick = onBackButton) {
                    Icon(
                        modifier = Modifier.flipWithLanguage(),
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                        contentDescription = null,
                        tint = MaterialTheme.colors.primaryVariant
                    )
                }
            }
        } else null,
        actions = actions,
        backgroundColor = backgroundColor,
        elevation = elevation,
        contentColor = contentColor,
        modifier = modifier.fillMaxWidth()
    )
}


fun Modifier.flipWithLanguage(): Modifier {
    val isLayoutRtl = Locale.getDefault().layoutDirection == LayoutDirection.Rtl.ordinal
    return if (isLayoutRtl) this.scale(scaleX = -1f, scaleY = 1f) else this
}