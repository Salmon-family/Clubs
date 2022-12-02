package com.thechance.identity.ui.screen.signup

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.thechance.identity.ui.R
import com.thechance.identity.ui.composable.*
import com.thechance.identity.ui.spacer.SpacerVertical
import com.thechance.identity.ui.theme.LightPrimaryBlackColor
import com.thechance.identity.ui.theme.LightPrimaryBrandColor
import com.thechance.identity.ui.theme.LightSecondaryBlackColor
import com.thechance.identity.ui.theme.Typography

@Composable
fun SignUpUserInformationScreen(
    context: Context
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        item {
            BackButtonComposable() {}


            SpacerVertical(height = 36.dp)
            TextComposable(
                text = stringResource(id = R.string.sign_up),
                style = Typography.h1,
                color = LightPrimaryBlackColor,
                Modifier.padding(start = 8.dp)
            )

            SpacerVertical(height = 8.dp)
            EmailTextComposable(
                text1 = "Using ",
                color1 = LightSecondaryBlackColor,
                text2 = "motu.uiux@gmail.com",
                color2 = LightPrimaryBrandColor,
                text3 = " to login"
            )

            SpacerVertical(height = 24.dp)
            TextComposable(
                text = stringResource(id = R.string.full_naame),
                style = Typography.body2,
                color = LightSecondaryBlackColor,
                Modifier.padding(start = 8.dp)
            )

            SpacerVertical(height = 14.dp)
            InputTextComposable(
                type = KeyboardType.Text,
                placeHolder = stringResource(id = R.string.password_place_holder)
            ) {

            }

            SpacerVertical(height = 24.dp)
            TextComposable(
                text = stringResource(id = R.string.user_name),
                style = Typography.body2,
                color = LightSecondaryBlackColor,
                Modifier.padding(start = 8.dp)
            )

            SpacerVertical(height = 14.dp)
            InputTextComposable(
                type = KeyboardType.Text,
                placeHolder = stringResource(id = R.string.password_place_holder)
            ) {

            }

            SpacerVertical(height = 24.dp)
            TextComposable(
                text = stringResource(id = R.string.birth_date),
                style = Typography.body2,
                color = LightSecondaryBlackColor,
                Modifier.padding(start = 8.dp)
            )

            SpacerVertical(height = 14.dp)
            DatePickerComposable(
                image = R.drawable.ic_arrow_down_circle,
                context = context
            )

            SpacerVertical(height = 24.dp)
            TextComposable(
                text = stringResource(id = R.string.gender),
                style = Typography.body2,
                color = LightSecondaryBlackColor,
                Modifier.padding(start = 8.dp)
            )

            SpacerVertical(height = 14.dp)
//            InputTextComposable(
//                type = KeyboardType.Text,
//            ) {
//
//            }

            SpacerVertical(height = 24.dp)
            ButtonComposable(
                buttonModifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                onClick = {},
                text = stringResource(id = R.string.continue_label)
            )
        }
    }
}