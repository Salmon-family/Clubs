package com.thechance.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

@Composable
fun ArrowBack(
    modifier: Modifier = Modifier,
    icon: Int,
    contentDescription: Int,
) {
    Image(painter = painterResource(id = icon),
        contentDescription = stringResource(id = contentDescription), modifier = modifier)
}