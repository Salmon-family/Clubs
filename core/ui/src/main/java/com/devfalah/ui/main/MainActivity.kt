package com.devfalah.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.devfalah.ui.ClubsApp
import com.devfalah.ui.theme.ClubsTheme
import com.devfalah.ui.util.Language
import com.devfalah.viewmodels.main.MainViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        Language().updateResources(
            context = this,
            language = viewModel.uiState.value
        )

        setContent {
            ClubsTheme {
                val systemUIController = rememberSystemUiController()
                systemUIController.setNavigationBarColor(color = MaterialTheme.colors.background)
                systemUIController.setStatusBarColor(color = MaterialTheme.colors.background)

                if (viewModel.checkIfLoggedIn()) {
                    ClubsApp()
                } else {
                    navigateToIdentity()
                }
            }
        }
    }

    private fun navigateToIdentity() {
        try {
            val intent = Intent(
                this,
                Class.forName("com.thechance.identity.ui.main.AuthenticationActivity")
            )
            startActivity(intent)
            finish()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
    }

}
