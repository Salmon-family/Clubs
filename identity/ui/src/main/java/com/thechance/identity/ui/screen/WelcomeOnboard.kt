package com.thechance.identity.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.thechance.identity.ui.shape.ParallelogramShape
import com.thechance.identity.ui.R
import com.thechance.identity.ui.composable.ButtonComposable
import com.thechance.identity.ui.composable.WelcomeOnBoardImage
import com.thechance.identity.ui.spacer.SpacerVertical
import com.thechance.identity.ui.spacer.SpacerVertical8
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
                    shape = ParallelogramShape(72f),
                    color = Color.White
                ),
            contentAlignment = Alignment.Center
        ){
            WelcomeOnBoardImage(
                painter = painterResource(id = R.drawable.welcome),
                description = stringResource(id = R.string.welcome)
            )
        }

        SpacerVertical(height = 60.dp)
        Row(
            modifier = Modifier.padding(start = 24.dp)
        ) {
            Text(
                text = stringResource(id = R.string.club),
                style = Typography.subtitle1,
                color = LightPrimaryBrandColor
            )
            Text(
                text = "\uD83D\uDE4C",
                fontFamily = PlusJakartaSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        SpacerVertical8()
        Text(
            text = stringResource(id = R.string.have_fun),
            style = Typography.h1,
            color = LightPrimaryBlackColor,
            modifier = Modifier.padding(start = 24.dp)
        )

        SpacerVertical8()
        Text(
            text = stringResource(id = R.string.onboard_body),
            style = Typography.body1,
            color = LightTernaryBlackColor,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        SpacerVertical(height = 20.dp)
        ButtonComposable(
            onClick = {},
            text = stringResource(id = R.string.lets_do),
            buttonModifier = Modifier
                .wrapContentSize().padding(start = 24.dp),
            textModifier = Modifier
                .padding(vertical = 12.dp, horizontal = 32.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWelcomeOnboard(){
    WelcomeOnboard()
}