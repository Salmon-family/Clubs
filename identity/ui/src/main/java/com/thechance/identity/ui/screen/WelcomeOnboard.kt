package com.thechance.identity.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thechance.identity.ui.ParallelogramShape
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
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .height(292.dp)
                .background(
                    shape = ParallelogramShape(),
                    color = Color.White
                ),
            contentAlignment = Alignment.Center
        ){
            OnBoardImage(
                painter = painterResource(id = R.drawable.welcome),
                description = stringResource(id = R.string.welcome)
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        Row(
            modifier = Modifier.padding(start = 24.dp)
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
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.have_fun),
            style = MaterialTheme.typography.Title,
            color = LightPrimaryBlackColor,
            modifier = Modifier.padding(start = 24.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.onboard_body),
            style = MaterialTheme.typography.body1,
            color = LightTernaryBlackColor,
            modifier = Modifier.padding(start = 24.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        OnboardButton(
            text = stringResource(id = R.string.lets_do)
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWelcomeOnboard(){
    WelcomeOnboard()
}