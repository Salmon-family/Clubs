package com.thechance.ui.composables

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.thechance.ui.theme.BlackColor

@Composable
fun TopBarTitle() {
    Text(text = "Chats", fontSize = 24.sp, fontWeight = FontWeight.SemiBold, color = BlackColor)
}