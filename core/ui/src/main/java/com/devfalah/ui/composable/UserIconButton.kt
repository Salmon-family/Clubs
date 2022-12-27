package com.devfalah.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import com.devfalah.viewmodels.friendRequest.UserState

@Composable
fun UserIconButton(
    modifier: Modifier = Modifier,
    userState: UserState,
    onButtonClick: (Int) -> Unit,
    iconsSize: Dp,
    painter: Painter
) {
    IconButton(
        modifier = modifier.size(iconsSize),
        onClick = { onButtonClick(userState.userID) },
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            Modifier.fillMaxSize()
        )
    }
}