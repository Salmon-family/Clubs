package com.devfalah.ui.screen.clubsDetail.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.devfalah.ui.R
import com.devfalah.ui.theme.LightBackgroundColor
import com.devfalah.ui.theme.LightQuaternaryBlackColor
import com.devfalah.ui.theme.LightSecondaryBlackColor
import com.devfalah.viewmodels.friendRequest.UserState

@Composable
fun OutlineButton(
    modifier: Modifier,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        onClick = { onClick() },
        border = BorderStroke(1.dp, MaterialTheme.colors.onSecondary),
        shape = RoundedCornerShape(100),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colors.onSurface,
            backgroundColor = MaterialTheme.colors.background
        ),
    ) {
        Text(text = stringResource(id = R.string.joined))
    }
}