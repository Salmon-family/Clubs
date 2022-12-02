package com.thechance.identity.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.thechance.identity.ui.screen.signup.SignUpUserInformationScreen
import com.thechance.identity.viewmodel.login.LoginViewModel
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
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val test = viewModel.uiState.collectAsState()
//                    SignUpEmailScreen()
//                    WelcomeOnboard()
//                    OnBoardingPagerScreen()
//                    EmailAddressScreen()
//                    SignUpConfirmPassword()
                    SignUpUserInformationScreen(this)
                }
            }
        }
    }
}
