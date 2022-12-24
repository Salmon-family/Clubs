package com.devfalah.ui.screen.postDetails.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.devfalah.ui.R

@Composable
fun MenuButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier.padding(4.dp).clickable { onClick() },
        painter = painterResource(id = R.drawable.menu_alt),
        contentDescription = "menu button"
    )
}
