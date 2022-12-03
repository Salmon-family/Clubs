package com.devfalah.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.devfalah.ui.R
import com.devfalah.ui.theme.LightPrimaryBrandColor

@Composable
fun NotificationIcon(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.padding(top = 8.dp)) {
        Image(
            modifier = Modifier
                .size(10.dp)
                .fillMaxHeight(),
            colorFilter = ColorFilter.tint(LightPrimaryBrandColor),
            painter = painterResource(R.drawable.ic_circle),
            contentDescription = stringResource(id = R.string.notification_icon),
        )
    }
}