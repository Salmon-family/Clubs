package com.devfalah.ui.screen.clubsDetail.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.devfalah.ui.R

@Composable
fun OutlineButton(
    modifier: Modifier
) {
    OutlinedButton(
        modifier = modifier,
        shape = RoundedCornerShape(100.dp),
        elevation = ButtonDefaults.elevation(0.dp),
        enabled = false,
        onClick = {}
    ) {
        Text(text = stringResource(id = R.string.joined))
    }
}