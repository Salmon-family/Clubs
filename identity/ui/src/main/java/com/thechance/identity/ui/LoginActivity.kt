package com.thechance.identity.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.thechance.identity.ui.screen.signup.SignUpUserInformationScreen
import com.thechance.identity.ui.theme.IdentityTheme
import com.thechance.identity.ui.theme.LightPrimaryBlackColor
import com.thechance.identity.viewmodel.login.LoginViewModel
import com.thechance.identity.viewmodel.signup.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    private val viewModel: SignupViewModel by viewModels()

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
                    Greeting(test.value.firstName)
//                    SignUpEmailScreen()
//                    WelcomeOnboard()
//                    OnBoardingPagerScreen()
//                    EmailAddressScreen()
//                    SignUpConfirmPasswordScreen()
//                    SignUpUserInformationScreen(context = this)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!", color = LightPrimaryBlackColor)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    IdentityTheme {
        Greeting("Android")
    }
}

