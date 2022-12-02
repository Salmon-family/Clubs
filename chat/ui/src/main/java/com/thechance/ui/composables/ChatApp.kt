package com.thechance.ui.composables

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.thechance.ui.screens.ChatsScreen
import com.thechance.ui.theme.BlackColor

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ChatApp() {
    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color.White,
            contentColor = BlackColor,
        ) {
            TopBarTitle()
        }
    }) {
        ChatsScreen()
    }
}