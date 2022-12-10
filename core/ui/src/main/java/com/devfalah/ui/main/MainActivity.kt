package com.devfalah.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.devfalah.ui.ClubsApp
import com.devfalah.ui.theme.ClubsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            val systemUIController
            ClubsTheme {
                ClubsApp()
            }
        }
    }
}
