package com.thechance.identity.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thechance.identity.ui.R
import com.thechance.identity.ui.composable.OnBoardImage
import com.thechance.identity.ui.composable.OnboardButton
import com.thechance.identity.ui.theme.*

@Composable
fun WelcomeOnboard(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightSecondaryBrandColor)
            .padding(horizontal = 24.dp)
    ) {
        OnBoardImage(
            painter = painterResource(id = R.drawable.welcome),
            description = stringResource(id = R.string.welcome)
        )

        Row(
            modifier = Modifier.padding(top = 90.dp)
        ) {
            Text(
                text = stringResource(id = R.string.club),
                style = MaterialTheme.typography.subtitle1,
                color = LightPrimaryBrandColor
            )
            Text(
                text = "\uD83D\uDE4C",
                fontFamily = Inter,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Text(
            text = stringResource(id = R.string.have_fun),
            style = MaterialTheme.typography.Title,
            color = LightPrimaryBlackColor,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = stringResource(id = R.string.onboard_body),
            style = MaterialTheme.typography.body1,
            color = LightTernaryBlackColor,
            modifier = Modifier.padding(top = 8.dp)
        )

        OnboardButton(
            text = stringResource(id = R.string.lets_do)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWelcomeOnboard(){
    WelcomeOnboard()
}