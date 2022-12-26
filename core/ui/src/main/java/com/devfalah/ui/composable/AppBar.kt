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
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.showingBack
import com.devfalah.ui.theme.LightBackgroundColor

@Composable
fun AppBar(
    title: String,
    navHostController: NavController,
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = MaterialTheme.colors.background,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = 0.dp
) {
    TopAppBar(
        title = {
            Text(
                text = title,
            )
        },
        navigationIcon = if (navHostController.showingBack()) {
            {
                IconButton(onClick =navHostController::popBackStack) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                        contentDescription = null,
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