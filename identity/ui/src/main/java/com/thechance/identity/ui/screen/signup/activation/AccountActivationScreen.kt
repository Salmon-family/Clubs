package com.thechance.identity.ui.screen.signup.activation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thechance.identity.ui.R
import com.thechance.identity.ui.composable.AuthButton
import com.thechance.identity.ui.composable.BackButton
import com.thechance.identity.ui.screen.signup.composable.ActivationImage
import com.thechance.identity.ui.spacer.SpacerVertical16
import com.thechance.identity.ui.theme.*

@Composable
fun AccountActivationScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BackButton(onClick = {})
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
            text = stringResource(id = R.string.open_gmail),
            style = Typography.subtitle2,
            color = LightPrimaryBrandColor,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
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

@Preview(showBackground = true)
@Composable
fun PreviewAccountActivationScreen(){
    AccountActivationScreen()
}