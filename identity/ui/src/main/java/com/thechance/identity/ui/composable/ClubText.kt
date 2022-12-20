package com.thechance.identity.ui.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thechance.identity.ui.R
import com.thechance.identity.ui.theme.LightPrimaryBrandColor
import com.thechance.identity.ui.theme.PlusJakartaSans
import com.thechance.identity.ui.theme.Typography

@Composable
fun ClubText() {
    Row(
        modifier = Modifier.padding(start = 24.dp)
    ) {
        Text(
            text = stringResource(id = R.string.club),
            style = Typography.subtitle1,
            color = LightPrimaryBrandColor
        )
        Text(
            text = "\uD83D\uDE4C",
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}