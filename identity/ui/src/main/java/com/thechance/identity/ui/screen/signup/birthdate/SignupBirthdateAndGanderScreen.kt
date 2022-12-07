package com.thechance.identity.ui.screen.signup.birthdate

import android.os.Build
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.thechance.identity.ui.R
import com.thechance.identity.ui.composable.AuthButton
import com.thechance.identity.ui.composable.BackButton
import com.thechance.identity.ui.screen.signup.composable.DatePicker
import com.thechance.identity.ui.composable.EmailDescriptionText
import com.thechance.identity.ui.screen.activation.navigateToAccountActivation
import com.thechance.identity.ui.spacer.SpacerVertical24
import com.thechance.identity.ui.spacer.SpacerVertical8
import com.thechance.identity.ui.theme.LightPrimaryBlackColor
import com.thechance.identity.ui.theme.LightPrimaryBrandColor
import com.thechance.identity.ui.theme.LightSecondaryBlackColor
import com.thechance.identity.ui.theme.Typography
import com.thechance.identity.viewmodel.signup.SignupViewModel
import com.thechance.identity.viewmodel.signup.UserUIState

@Composable
fun SignupBirthdateAndGenderScreen(
    navController: NavController,
    viewModel: SignupViewModel,
) {
    val state by viewModel.uiState.collectAsState()
    SignupBirthdateAndGanderContent(
        state,
        onChangeGender = viewModel::onChangeGender,
        onChangeBirthdate = viewModel::onChangeBirthdate,
        onClickBack = { navController.navigateUp() },
        onCreateAccount = {
            viewModel.makeSignupRequest()
            navController.navigateToAccountActivation()
        }
    )
}

@Composable
private fun SignupBirthdateAndGanderContent(
    state: UserUIState,
    onClickBack: () -> Unit,
    onChangeBirthdate: (String) -> Unit,
    onChangeGender: (String) -> Unit,
    onCreateAccount: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {

        BackButton(onClick = onClickBack)

        SpacerVertical24()
        Text(
            text = stringResource(id = R.string.sign_up),
            style = Typography.h1,
            color = LightPrimaryBlackColor,
            modifier = Modifier.padding(start = 8.dp)
        )

        SpacerVertical8()
        EmailDescriptionText(
            text1 = stringResource(id = R.string.using),
            color1 = LightSecondaryBlackColor,
            text2 = state.email,
            color2 = LightPrimaryBrandColor,
            text3 = stringResource(id = R.string.to_login)
        )

        SpacerVertical24()
        Text(
            text = stringResource(id = R.string.birth_date),
            style = Typography.body2,
            color = LightSecondaryBlackColor,
            modifier = Modifier.padding(start = 8.dp)
        )

        Spacer(Modifier.height(14.dp))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            DatePicker(
                image = R.drawable.ic_arrow_down_circle,
            )
        }

        SpacerVertical24()
        Text(
            text = stringResource(id = R.string.gender),
            style = Typography.body2,
            color = LightSecondaryBlackColor,
            modifier = Modifier.padding(start = 8.dp)
        )

        SpacerVertical24()
        AuthButton(
            onClick = onCreateAccount,
            buttonModifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            isEnabled = true,
            text = stringResource(id = R.string.create_account_label),
        )
    }

}