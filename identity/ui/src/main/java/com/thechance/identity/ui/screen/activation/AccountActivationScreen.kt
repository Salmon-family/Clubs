package com.thechance.identity.ui.screen.activation

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.thechance.identity.ui.R
import com.thechance.identity.ui.composable.AuthButton
import com.thechance.identity.ui.composable.BackButton
import com.thechance.identity.ui.screen.login.username.navigateToLogInUserName
import com.thechance.identity.ui.screen.onboarding.pager.ON_BOARDING_PAGER_Route
import com.thechance.identity.ui.screen.signup.composable.BackPressHandler
import com.thechance.identity.ui.spacer.SpacerVertical16
import com.thechance.identity.ui.spacer.SpacerVertical24
import com.thechance.identity.ui.theme.*

@Composable
fun AccountActivationScreen(
    navController: NavController
) {
    val context = LocalContext.current

    fun onBack() = navController.popBackStack(route = ON_BOARDING_PAGER_Route, inclusive = false)
    BackPressHandler(onBackPressed = { onBack() })

    AccountActivationContent(
        onclickBack = { onBack() },
        onClickOpenEmail = {
            try {
                val intent = Intent(Intent.ACTION_MAIN)
                intent.addCategory(Intent.CATEGORY_APP_EMAIL)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }catch (e: Exception){
                Toast.makeText(context, R.string.not_exist_email, Toast.LENGTH_SHORT).show()
            }
        },
        navigateToLoginScreen = {
            navController.navigateToLogInUserName()
        }
    )
}

@Composable
private fun AccountActivationContent(
    onclickBack: () -> Unit,
    onClickOpenEmail: () -> Unit,
    navigateToLoginScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        SpacerVertical16()
        BackButton(onClick = onclickBack)

        SpacerVertical24()
        Text(
            text = stringResource(id = R.string.account_activation_title),
            style = Typography.h1,
            color = MaterialTheme.colors.primaryVariant,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center

        )

        SpacerVertical16()
        Box(Modifier.weight(1f)) {
            ActivationImage()
        }

        SpacerVertical16()
        Text(
            text = stringResource(id = R.string.account_activation_body),
            style = Typography.InputText,
            color = MaterialTheme.colors.secondaryVariant,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        SpacerVertical16()
        Text(
            text = stringResource(id = R.string.open_email),
            style = Typography.subtitle2,
            color = LightPrimaryBrandColor,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClickOpenEmail),
        )

        Spacer(
            Modifier
                .weight(1f)
                .padding(8.dp)
        )
        AuthButton(
            onClick = navigateToLoginScreen,
            isEnabled = true,
            text = stringResource(id = R.string.log_in),
            buttonModifier = Modifier.fillMaxWidth()
        )
        SpacerVertical16()
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewAccountActivationScreen() {
    AccountActivationContent({}, {}, {})
}