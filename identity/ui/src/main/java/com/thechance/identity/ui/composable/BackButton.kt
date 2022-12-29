package com.thechance.identity.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.thechance.identity.ui.R

@Composable
fun BackButton(
    onClick: () -> Unit,
    isEnabled: Boolean = true
) {
    Column(
        modifier = Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.Start
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back_arrow),
            contentDescription = null,
            modifier = Modifier.clickable(
                onClick = onClick,
                enabled = isEnabled
            ),
            tint = MaterialTheme.colors.primaryVariant
        )
    }
}