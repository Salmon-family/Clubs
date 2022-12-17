package com.devfalah.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.devfalah.ui.R
import com.devfalah.ui.showingBack
import com.devfalah.ui.theme.LightBackgroundColor

@Composable
fun AppBar(
    title: String,
    navHostController: NavController,
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
                        contentDescription = null
                    )
                }
            }
        } else null,
        backgroundColor = LightBackgroundColor,
        elevation = 0.dp,
        modifier = Modifier.fillMaxWidth()
    )
}