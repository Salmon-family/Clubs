package com.thechance.identity.ui.screen.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.thechance.identity.ui.R
import com.thechance.identity.ui.composable.BackButtonComposable
import com.thechance.identity.ui.composable.ButtonComposable
import com.thechance.identity.ui.composable.InputTextComposable
import com.thechance.identity.ui.composable.TextComposable
import com.thechance.identity.ui.spacer.SpacerVertical
import com.thechance.identity.ui.theme.LightPrimaryBlackColor
import com.thechance.identity.ui.theme.LightSecondaryBlackColor
import com.thechance.identity.ui.theme.Typography
import com.thechance.identity.viewmodel.signup.SignupViewModel

@Preview(showSystemUi = true)
@Composable
fun SignUpEmailContent() {
    SignUpEmailContent({}, {})
}

@Composable
fun SignUpEmailScreen(
    navController: NavController,
    viewModel: SignupViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    SignUpEmailContent(
        onClickPasswordScreen = { navController.navigate("signupConfirmPasswordScreen") },
        onClickBack = { navController.navigateUp() }
    )
}

@Composable
private fun SignUpEmailContent(
    onClickPasswordScreen: () -> Unit,
    onClickBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        BackButtonComposable { onClickBack }

        SpacerVertical(height = 36.dp)
        TextComposable(
            text = stringResource(id = R.string.sign_up),
            style = Typography.h1,
            color = LightPrimaryBlackColor,
            Modifier.padding(start = 8.dp)
        )

        SpacerVertical(height = 24.dp)
        TextComposable(
            text = stringResource(id = R.string.email),
            style = Typography.subtitle2,
            color = LightSecondaryBlackColor,
            Modifier.padding(start = 8.dp)
        )

        SpacerVertical(height = 14.dp)
        InputTextComposable(
            type = KeyboardType.Email,
            image = R.drawable.ic_close,
            placeHolder = stringResource(id = R.string.email_place_holder),
            text = "",
            onTextChange = {}
        ) {

        }

        SpacerVertical(height = 24.dp)
        ButtonComposable(
            buttonModifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            onClick = onClickPasswordScreen,
            text = stringResource(id = R.string.continue_label)
        )
    }
}