package com.thechance.ui

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.thechance.ui.screens.conversation.navigateToConversation
import com.thechance.ui.theme.ClubsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        val friendId = intent.extras?.getInt("FRIEND_ID", -1) ?: -1
        setContent {
            ClubsTheme {
                val systemUIController = rememberSystemUiController()
                systemUIController.setNavigationBarColor(color = MaterialTheme.colors.background)
                systemUIController.setStatusBarColor(MaterialTheme.colors.background, darkIcons = true)

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val navController = rememberNavController()
                    ChatNavGraph(navController = navController, START_DESTINATION)
                    if (friendId != -1 ) {
                        navController.navigateToConversation(friendId)
                    }
                }
            }
        }
    }
}