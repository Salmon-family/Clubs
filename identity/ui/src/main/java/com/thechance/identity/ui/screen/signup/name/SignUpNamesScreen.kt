package com.thechance.identity.ui.screen.signup.name

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.thechance.identity.ui.R
import com.thechance.identity.ui.composable.*
import com.thechance.identity.ui.screen.signup.birthdate.navigateToBirthdateAndGander
import com.thechance.identity.ui.spacer.SpacerVertical24
import com.thechance.identity.ui.spacer.SpacerVertical8
import com.thechance.identity.ui.theme.LightPrimaryBlackColor
import com.thechance.identity.ui.theme.LightPrimaryBrandColor
import com.thechance.identity.ui.theme.LightSecondaryBlackColor
import com.thechance.identity.ui.theme.Typography
import com.thechance.identity.viewmodel.signup.SignupViewModel
import com.thechance.identity.viewmodel.signup.UserUIState

@Composable
fun SignUpFullNameScreen(
    navController: NavController,
    viewModel: SignupViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    SignUpFullNameContent(
        state,
        onChangeFullName = viewModel::onChangeFullName,
        onChangeUserName = viewModel::onChangeUserName,
        onValidate = viewModel::onValidateName,
        onClickBack = { navController.navigateUp() },
        onClickUserNameScreen = { navController.navigateToBirthdateAndGander() },
    )
}

@Composable
private fun SignUpFullNameContent(
    state: UserUIState,
    onClickBack: () -> Unit,
    onClickUserNameScreen: () -> Unit,
    onChangeFullName: (String) -> Unit,
    onChangeUserName: (String) -> Unit,
    onValidate: () -> Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {

        BackButton(onClick = onClickBack)

        SpacerVertical24()
        AuthText(
            text = stringResource(id = R.string.sign_up),
            style = Typography.h1,
            color = LightPrimaryBlackColor,
            Modifier.padding(start = 8.dp)
        )

        SpacerVertical8()
        EmailDescriptionText(
            text1 = stringResource(id = R.string.using),
            color1 = LightSecondaryBlackColor,
            text2 = stringResource(id = R.string.email_place_holder),
            color2 = LightPrimaryBrandColor,
            text3 = stringResource(id = R.string.to_login)
        )

        SpacerVertical24()
        AuthText(
            text = stringResource(id = R.string.full_naame),
            style = Typography.body2,
            color = LightSecondaryBlackColor,
            Modifier.padding(start = 8.dp)
        )

        Spacer(Modifier.height(14.dp))
        InputText(
            type = KeyboardType.Text,
            placeHolder = "ali",
            text = state.firstName,
            onTextChange = onChangeFullName
        )

        SpacerVertical24()
        AuthText(
            text = stringResource(id = R.string.user_name),
            style = Typography.body2,
            color = LightSecondaryBlackColor,
            Modifier.padding(start = 8.dp)
        )

        Spacer(Modifier.height(14.dp))
        InputText(
            type = KeyboardType.Text,
            placeHolder = stringResource(id = R.string.user_name_hint),
            text = state.username,
            onTextChange = onChangeUserName
        )

        SpacerVertical24()
        AuthButton(
            onClick = onClickUserNameScreen,
            buttonModifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            isEnabled = onValidate.invoke(),
            text = stringResource(id = R.string.continue_label),
        )
    }

}