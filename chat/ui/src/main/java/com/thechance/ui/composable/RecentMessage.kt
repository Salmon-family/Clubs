package com.thechance.ui.composable

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.thechance.ui.parseHtml
import com.thechance.ui.theme.PlusJakartaSans

@Composable
fun RecentMessage(
    message: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = message.parseHtml(),
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        modifier = modifier,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        fontFamily = PlusJakartaSans
    )

}