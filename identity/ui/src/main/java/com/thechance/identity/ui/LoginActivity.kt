package com.thechance.identity.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.thechance.identity.ui.screen.WelcomeOnboard
import com.thechance.identity.viewmodel.LoginViewModel
import com.thechance.identity.ui.theme.IdentityTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IdentityTheme {
                // A surface container using the 'background' color from the theme
                WelcomeOnboard()
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    val test = viewModel.uiState.collectAsState()
//                }
            }
        }
    }
}
