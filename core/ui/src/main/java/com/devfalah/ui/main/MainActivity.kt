package com.devfalah.ui.main

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import com.devfalah.ui.ClubsApp
import com.devfalah.ui.composable.SetStatusBarColor
import com.devfalah.ui.screen.postDetails.PostDetailsScreen
import com.devfalah.ui.screen.postDetails.composable.InputDialogView
import com.devfalah.ui.theme.ClubsTheme
import com.devfalah.ui.theme.LightPrimaryBrandColor
import com.devfalah.ui.theme.WhiteColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SetStatusBarColor()
            ClubsTheme {
//                ClubsApp()
                PostDetailsScreen()
            }
        }
    }
}
