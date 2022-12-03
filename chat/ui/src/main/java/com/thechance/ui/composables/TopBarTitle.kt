package com.thechance.ui.composables

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.thechance.ui.theme.BlackColor

@Composable
fun TopBarTitle(
    modifier: Modifier = Modifier,
    title: Int,
) {
    Text(modifier = modifier,
        text = stringResource(id = title),
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold,
        color = BlackColor)
}