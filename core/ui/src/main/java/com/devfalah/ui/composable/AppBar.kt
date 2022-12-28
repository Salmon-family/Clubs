package com.devfalah.ui.composable

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.devfalah.ui.R

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
                text = title,
            )
        },
        navigationIcon = if (showBackButton) {
            {
                IconButton(onClick = onBackButton) {
                    Icon(
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