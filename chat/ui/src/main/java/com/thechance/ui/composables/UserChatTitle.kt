package com.thechance.ui.composables

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.thechance.ui.extensions.minimizeLongString
import com.thechance.ui.theme.BlackColor

@Composable
fun UserChatTitle(
    userName: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = userName.minimizeLongString(25),
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        color = BlackColor,
        modifier = modifier
    )
}