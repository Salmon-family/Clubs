package com.devfalah.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.devfalah.ui.screen.NotificationScreen
import com.devfalah.ui.theme.ClubsTheme
import com.devfalah.viewmodels.NotificationsViewModel
import com.devfalah.viewmodels.UserFriendsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClubsTheme {
                NotificationScreen()
            }
        }
    }
}
