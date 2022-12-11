package com.thechance.identity.ui.screen.activation

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.thechance.identity.ui.R
import com.thechance.identity.ui.composable.AuthButton
import com.thechance.identity.ui.composable.BackButton
import com.thechance.identity.ui.screen.login.username.navigateToLogInUserName
import com.thechance.identity.ui.screen.onboarding.pager.ON_BOARDING_PAGER_Route
import com.thechance.identity.ui.screen.onboarding.pager.navigateToOnBoardingPager
import com.thechance.identity.ui.spacer.SpacerVertical16
import com.thechance.identity.ui.theme.*

@Composable
fun AccountActivationScreen(
    navController: NavController
){
    val context = LocalContext.current
    AccountActivationContent(
        onclickBack = {navController.popBackStack(route = ON_BOARDING_PAGER_Route, inclusive = false)},
        onClickOpenEmail = {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = Constants.MESSAGE_TYPE
            context.startActivity(intent)
        }
    )
}

@Composable
private fun AccountActivationContent(
    onclickBack: () -> Unit,
    onClickOpenEmail: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BackButton(onClick = onclickBack)
        Spacer(modifier = Modifier.weight(0.25f))
        Text(
            text = stringResource(id = R.string.account_activation_title),
            style = Typography.h1,
            color = LightPrimaryBlackColor,
            modifier = Modifier.wrapContentSize()
        )
        SpacerVertical16()
        ActivationImage()
        SpacerVertical16()
        Text(
            text = stringResource(id = R.string.account_activation_body),
            style = Typography.InputText,
            color = LightTernaryBlackColor,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        SpacerVertical16()
        Text(
            text = stringResource(id = R.string.open_email),
            style = Typography.subtitle2,
            color = LightPrimaryBrandColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
                .clickable(onClick = onClickOpenEmail),
        )
        Spacer(Modifier.weight(1f))
        AuthButton(
            onClick = {},
            isEnabled = true,
            text = stringResource(id = R.string.log_in),
            buttonModifier = Modifier.fillMaxWidth()
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewAccountActivationScreen(){
//    AccountActivationContent()
//}