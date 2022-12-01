package com.thechance.identity.ui.screen.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thechance.identity.ui.R
import com.thechance.identity.ui.composable.BackButtonComposable
import com.thechance.identity.ui.composable.ButtonComposable
import com.thechance.identity.ui.composable.InputTextComposable
import com.thechance.identity.ui.spacer.SpacerVertical
import com.thechance.identity.ui.spacer.SpacerVertical12
import com.thechance.identity.ui.spacer.SpacerVertical24
import com.thechance.identity.ui.theme.LightPrimaryBlackColor
import com.thechance.identity.ui.theme.LightSecondaryBlackColor
import com.thechance.identity.ui.theme.Typography

@Composable
fun EmailAddress(){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SpacerVertical12()
        BackButtonComposable {

        }

        SpacerVertical(52.dp)
        Text(
            text = stringResource(id = R.string.email_address_question),
            style = Typography.h1,
            color = LightPrimaryBlackColor,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        SpacerVertical24()
        Text(
            text = stringResource(id = R.string.email_label),
            style = Typography.subtitle2,
            color = LightSecondaryBlackColor,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        SpacerVertical(height = 14.dp)
        InputTextComposable(
            type = KeyboardType.Email
        ) {

        }

        SpacerVertical24()
        ButtonComposable(
            onClick = {},
            text = stringResource(id = R.string.continue_label),
            buttonModifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEmailAddress(){
    EmailAddress()
}