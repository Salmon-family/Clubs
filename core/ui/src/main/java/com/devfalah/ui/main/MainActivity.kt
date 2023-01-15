package com.devfalah.ui.main

import android.Manifest.permission.POST_NOTIFICATIONS
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.devfalah.ui.ClubsApp
import com.devfalah.ui.theme.ClubsTheme
import com.devfalah.viewmodels.main.MainViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        val requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) {}
        setContent {
            ClubsTheme {
                val systemUIController = rememberSystemUiController()
                systemUIController.setNavigationBarColor(color = MaterialTheme.colors.background)
                systemUIController.setStatusBarColor(color = MaterialTheme.colors.background)

                if (viewModel.checkIfLoggedIn()) {
                    ClubsApp()
                    askForNotificationPermission(requestPermissionLauncher)
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

    private fun askForNotificationPermission(requestPermissionLauncher: ActivityResultLauncher<String>) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissionLauncher.launch(POST_NOTIFICATIONS)
            }
        }
    }

}
