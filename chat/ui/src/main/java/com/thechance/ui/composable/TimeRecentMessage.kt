package com.thechance.ui.composable

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.thechance.ui.theme.PlusJakartaSans

@Composable
fun TimeRecentMessage(
    timeRecent: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = timeRecent,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        modifier = modifier,
        fontFamily = PlusJakartaSans
    )
}