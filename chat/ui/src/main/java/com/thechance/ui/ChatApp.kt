package com.thechance.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import com.thechance.ui.screens.chats.ChatsScreen
import com.thechance.ui.theme.ClubsTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ChatApp() {
    ClubsTheme {
        ChatsScreen()
    }
}
