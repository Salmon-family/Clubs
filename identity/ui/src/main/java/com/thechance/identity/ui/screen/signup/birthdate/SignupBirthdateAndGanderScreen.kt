package com.thechance.identity.ui.screen.signup.birthdate

import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.thechance.identity.ui.R
import com.thechance.identity.ui.composable.*
import com.thechance.identity.ui.screen.login.username.navigateToLogInUserName
import com.thechance.identity.ui.screen.signup.clubs.navigateToClubs
import com.thechance.identity.ui.screen.signup.composable.DatePicker
import com.thechance.identity.ui.screen.signup.composable.SegmentControls
import com.thechance.identity.ui.spacer.SpacerVertical24
import com.thechance.identity.ui.spacer.SpacerVertical8
import com.thechance.identity.ui.theme.Typography
import com.thechance.identity.viewmodel.signup.SignupViewModel
import com.thechance.identity.viewmodel.signup.UserUIState
import com.thechance.identity.viewmodel.utils.ErrorMessageType

@Composable
fun SignupBirthdateAndGenderScreen(
    navController: NavController,
    viewModel: SignupViewModel,
) {

    val state by viewModel.uiState.collectAsState()

    SignupBirthdateAndGanderContent(
        state = state,
        onChangeGender = viewModel::onChangeGender,
        onChangeBirthdate = viewModel::onChangeBirthdate,
        onClickBack = { navController.navigateUp() },
        onNavigate = { navController.navigateToLogInUserName() },
        onCreateAccount = viewModel::onSignup,
        navController = navController

    )

}

@Composable
private fun SignupBirthdateAndGanderContent(
    state: UserUIState,
    onClickBack: () -> Unit,
    onChangeBirthdate: (String) -> Unit,
    onChangeGender: (String) -> Unit,
    onCreateAccount: () -> Unit,
    onNavigate: () -> Unit,
    navController: NavController,
) {

    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
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
                color = MaterialTheme.colors.primaryVariant,
                modifier = Modifier.padding(start = 8.dp)
            )

            SpacerVertical8()
            EmailDescriptionText(
                email = state.email,
            )

            SpacerVertical24()
            Text(
                text = stringResource(id = R.string.birth_date),
                style = Typography.body2,
                color = MaterialTheme.colors.onSecondary,
                modifier = Modifier.padding(start = 8.dp)
            )

            Spacer(Modifier.height(14.dp))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                DatePicker(
                    birthDate = state.birthdate,
                    onDateChange = onChangeBirthdate
                )
            }

            SpacerVertical24()
            Text(
                text = stringResource(id = R.string.gender),
                style = Typography.body2,
                color = MaterialTheme.colors.onSecondary,
                modifier = Modifier.padding(start = 8.dp)
            )

            SpacerVertical8()
            SegmentControls(
                onChangeGander = onChangeGender
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

            if (state.errorTypeValue != ErrorMessageType.NO_ERROR.value) {
                Error(
                    errorMessage = when (state.errorTypeValue) {
                        ErrorMessageType.INVALID_USERNAME.value -> stringResource(R.string.invalid_username_message)
                        else -> stringResource(id = R.string.unknown_error_message)
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            NavigateToAnotherScreen(
                hintText = R.string.navigate_to_login,
                navigateText = R.string.log_in,
                onNavigate = onNavigate
            )
            SpacerVertical24()

        }

        if (state.isLoading) {
            Loading()
        }

    }

    LaunchedEffect(key1 = state.isLoading) {
        if (state.isSuccess) {
            navController.navigateToClubs()
            Toast.makeText(context, R.string.success_message, Toast.LENGTH_SHORT).show()
        }
    }
}