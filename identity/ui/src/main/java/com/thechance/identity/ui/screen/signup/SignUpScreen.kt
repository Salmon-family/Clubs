package com.thechance.identity.ui.screen.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thechance.identity.ui.R
import com.thechance.identity.ui.composable.BackButtonComposable
import com.thechance.identity.ui.composable.ButtonComposable
import com.thechance.identity.ui.composable.TextComposable
import com.thechance.identity.ui.composable.TextFieldComposable
import com.thechance.identity.ui.spacer.SpacerVertical
import com.thechance.identity.ui.theme.LightPrimaryBlackColor
import com.thechance.identity.ui.theme.Typography

@Preview(showSystemUi = true)
@Composable
fun SignUpScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        BackButtonComposable(backButton = R.drawable.ic_arrow_back)

        SpacerVertical(height = 36.dp)
        TextComposable(
            text = stringResource(id = R.string.sign_up),
            style = Typography.h1,
            color = LightPrimaryBlackColor,
            Modifier.padding(start = 24.dp)
        )

        SpacerVertical(height = 24.dp)
        TextFieldComposable("Motu.uiux@gmail.com") {}

        SpacerVertical(height = 24.dp)
        ButtonComposable(
            buttonModifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            onClick = {},
            text = stringResource(id = R.string.continue_text)
        )
    }
}